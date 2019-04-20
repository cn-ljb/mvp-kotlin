package com.ljb.mvp.kotlin.protocol.dao.impl

import android.database.Cursor
import com.ljb.mvp.kotlin.db.DatabaseSqlHelper
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.dao.IUserDaoProtocol
import com.ljb.mvp.kotlin.table.UserTable
import com.ljb.mvp.kotlin.utils.XLog
import dao.ljb.kt.core.BaseDaoProtocol
import io.reactivex.Observable

/**
 * Author:Ljb
 * Time:2019/4/20
 * There is a lot of misery in life
 **/
class UserDaoProtocol : BaseDaoProtocol(), IUserDaoProtocol {

    override fun queryUserByUserId(table: UserTable, userId: String): Observable<User> = createObservable {
        queryUserByUserIdImpl(table, userId) ?: User.EMPTY
    }

    override fun saveUser(table: UserTable, user: User): Observable<Boolean> = createObservable {
        saveUserImpl(table, user)
    }

    private fun saveUserImpl(table: UserTable, user: User): Boolean {
        val result: Boolean
        val dbUser = queryUserByUserIdImpl(table, user.id)
        if (dbUser == null) {
            val insert = insertUserImpl(table, user)
            result = insert != -1L
        } else {
            val update = updateUserImpl(table, user)
            result = update > 0
        }
        return result
    }

    private fun updateUserImpl(table: UserTable, user: User): Int {
        var result = 0
        try {
            val contentValue = DatabaseSqlHelper.getUserContentValue(table, user)
            result = mSqliteDb.update(table.getName(), contentValue, "${table.COLUMN_USER_ID} = ?", arrayOf(user.id))
        } catch (e: Exception) {
            XLog.e(e)
        }
        return result
    }

    private fun insertUserImpl(table: UserTable, user: User): Long {
        var result = -1L
        try {
            val contentValue = DatabaseSqlHelper.getUserContentValue(table, user)
            result = mSqliteDb.insert(table.getName(), null, contentValue)
        } catch (e: Exception) {
            XLog.e(e)
        }
        return result
    }

    private fun queryUserByUserIdImpl(table: UserTable, userId: String): User? {
        var user: User? = null
        var cursor: Cursor? = null
        try {
            cursor = mSqliteDb.rawQuery("select * from ${table.getName()} where ${table.COLUMN_USER_ID}= ?", arrayOf(userId))
            if (cursor != null && cursor.moveToNext()) {
                user = DatabaseSqlHelper.getUser(cursor, table)
            }
        } catch (e: Exception) {
            XLog.e(e)
        } finally {
            cursor?.close()
        }
        return user
    }

}
