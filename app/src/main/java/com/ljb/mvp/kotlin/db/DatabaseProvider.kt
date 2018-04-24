package com.ljb.mvp.kotlin.db

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.ljb.mvp.kotlin.common.Constant
import com.ljb.mvp.kotlin.common.Constant.DBProvider.TABLE_USERS
import java.util.*

/**
 * 数据库内容提供者
 * Created by L on 2017/7/11.
 */
class DatabaseProvider : ContentProvider() {

    companion object {
        //对外的Uri访问标识
        const val USER_CONTENT_URI = "content://" + Constant.DBProvider.AUTHORITY + "/" + TABLE_USERS.TABLE_NAME

        // URI匹配码
        private const val CODE_USER = 1
    }


    private val mDatabaseOpenHelper by lazy { DatabaseOpenHelper.getInstance(context) }
    private val mUriMatcher by lazy { UriMatcher(UriMatcher.NO_MATCH) }

    init {
        registerUri()
    }

    override fun onCreate(): Boolean {
        return true
    }

    private fun registerUri() {
        mUriMatcher.addURI(Constant.DBProvider.AUTHORITY, TABLE_USERS.TABLE_NAME, CODE_USER)
    }

    /**
     * 解析Uri查表名
     * */
    private fun matcherTable(uri: Uri): String {
        return when (mUriMatcher.match(uri)) {
            CODE_USER -> TABLE_USERS.TABLE_NAME
            else -> throw IllegalArgumentException("URI:$uri is not matched")
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val table = matcherTable(uri)
        var id: Long = -1L
        try {
            val db = mDatabaseOpenHelper.writableDatabase
            db.beginTransaction()
            try {
                id = db.insertOrThrow(table, null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
            if (id == -1L) {
                return null
            }
            context!!.contentResolver.notifyChange(uri, null)
            return ContentUris.withAppendedId(uri, id)
        } catch (e: SQLiteException) {
            return null
        }

    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val table = matcherTable(uri)
        return try {
            val qb = SQLiteQueryBuilder()
            qb.tables = table
            val db = mDatabaseOpenHelper.writableDatabase
            val cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)
            cursor.setNotificationUri(context!!.contentResolver, uri)
            cursor
        } catch (e: SQLiteException) {
            null
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val table = matcherTable(uri)
        try {
            var count = 0
            val db = mDatabaseOpenHelper.writableDatabase
            db.beginTransaction()
            try {
                count = db.update(table, values, selection, selectionArgs)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
            context!!.contentResolver.notifyChange(uri, null)
            return count
        } catch (e: SQLiteException) {
            return 0
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val table = matcherTable(uri)
        try {
            var count = 0
            val db = mDatabaseOpenHelper.writableDatabase
            db.beginTransaction()
            try {
                count = db.delete(table, selection, selectionArgs)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
            context!!.contentResolver.notifyChange(uri, null)
            return count
        } catch (e: SQLiteException) {
            return 0
        }
    }

    override fun bulkInsert(uri: Uri, values: Array<out ContentValues>): Int {
        val table = matcherTable(uri)
        var id: Long = -1L
        var numValues = 0
        try {
            val db = mDatabaseOpenHelper.writableDatabase
            db.beginTransaction()
            try {
                // 数据库操作
                numValues = values.size
                for (i in 0 until numValues) {
                    id = db.insertOrThrow(table, null, values[i])
                }
                db.setTransactionSuccessful() // Commit
            } finally {
                db.endTransaction()
            }
            if (id == -1L) {
                return -1
            }
        } catch (e: SQLiteException) {
            return -1
        }
        return numValues
    }

    override fun applyBatch(operations: ArrayList<ContentProviderOperation>?): Array<ContentProviderResult> {
        try {
            val db = mDatabaseOpenHelper.writableDatabase
            db.beginTransaction()
            try {
                val results = super.applyBatch(operations)
                db.setTransactionSuccessful()
                return results
            } finally {
                db.endTransaction()
            }
        } catch (e: SQLiteException) {
            throw OperationApplicationException()
        }
    }

    override fun getType(p0: Uri?): String? = null
}