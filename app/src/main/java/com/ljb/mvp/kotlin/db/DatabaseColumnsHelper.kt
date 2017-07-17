package com.ljb.mvp.kotlin.db

import com.ljb.mvp.kotlin.common.Constant.DBProvider.TABLE_USERS
import java.util.*

/**
 * Created by L on 2017/7/17.
 */
class DatabaseColumnsHelper {

    private val DATA_TYPE_TEXT = "TEXT"
    private val DATA_TYPE_LONG = "LONG"
    private val DATA_TYPE_INTEGER = "INTEGER"


    private val tables = HashMap<String, HashMap<String, String>>()

    init {
        initTableUserColumns()
    }

    /**
     * 生成建表的sql语句的一部分
     * @param tableName
     * *
     * @return
     */
    fun getCreateTableSql(tableName: String): String {
        val tabColumns = tables[tableName]
        val iterator = tabColumns!!.entries.iterator()
        val sql = StringBuilder()
        sql.append(" (")
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val key = entry.key
            val `val` = entry.value
            sql.append(" ").append(key).append(" ").append(`val`).append(", ")
        }
        sql.delete(sql.length - ", ".length, sql.length)
        sql.append(");")
        return sql.toString()
    }

    /**
     * 表的所有列名
     * @param table
     * *
     * @return
     */
    fun getColumns(table: String): Array<String> {
        val tabColumns = tables[table]
        return tabColumns!!.keys.toTypedArray()
    }

    /**
     * 所有表名
     * */
    fun getTableNames(): MutableSet<String> {
        return tables.keys
    }

    private fun initTableUserColumns() {
        val tableUserColumns = HashMap<String, String>()
        tableUserColumns.put(TABLE_USERS.COLUMN_ID, "integer primary key autoincrement")
        tableUserColumns.put(TABLE_USERS.COLUMN_LOGIN, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_USER_ID, DATA_TYPE_LONG)
        tableUserColumns.put(TABLE_USERS.COLUMN_AVATAR_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_GRAVATAR_ID, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_HTML_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_FOLLOWERS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_FOLLOWING_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_GISTS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_STARRED_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_SUBSCRIPTIONS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_ORGANIZATIONS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_REPOS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_EVENTS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_RECEIVED_EVENTS_URL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_TYPE, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_SITE_ADMIN, DATA_TYPE_INTEGER)
        tableUserColumns.put(TABLE_USERS.COLUMN_NAME, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_COMPANY, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_BLOG, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_LOCATION, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_EMAIL, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_HIREABLE, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_BIO, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_PUBLIC_REPOS, DATA_TYPE_INTEGER)
        tableUserColumns.put(TABLE_USERS.COLUMN_PUBLIC_GISTS, DATA_TYPE_INTEGER)
        tableUserColumns.put(TABLE_USERS.COLUMN_FOLLOWERS, DATA_TYPE_INTEGER)
        tableUserColumns.put(TABLE_USERS.COLUMN_FOLLOWING, DATA_TYPE_INTEGER)
        tableUserColumns.put(TABLE_USERS.COLUMN_CREATED_AT, DATA_TYPE_TEXT)
        tableUserColumns.put(TABLE_USERS.COLUMN_UPDATED_AT, DATA_TYPE_TEXT)
        tables.put(TABLE_USERS.TABLE_NAME, tableUserColumns)
    }
}