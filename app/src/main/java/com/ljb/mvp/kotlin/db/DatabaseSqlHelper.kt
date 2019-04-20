package com.ljb.mvp.kotlin.db

import android.content.ContentValues
import android.database.Cursor
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.table.UserTable
import dao.ljb.kt.table.BaseTable

/**
 * Author:Ljb
 * Time:2018/12/10
 * There is a lot of misery in life
 **/
object DatabaseSqlHelper {

    /**
     * 生成建表的sql语句的字段部分
     * @param table
     * @return sql
     */
    fun getCreateTableSql(table: BaseTable): String {
        val tabColumns = table.getColumns()
        val iterator = tabColumns.entries.iterator()
        val columnsSql = StringBuilder().apply {
            append(" (")
            while (iterator.hasNext()) {
                val entry = iterator.next()
                val key = entry.key
                val value = entry.value
                append(" ").append(key).append(" ").append(value).append(", ")
            }
            delete(this.length - ", ".length, this.length)
            append(");")
        }.toString()
        return "create table if not exists ${table.getName()}$columnsSql"
    }

    fun getUser(cursor: Cursor, table: UserTable): User {
        val login = cursor.getString(cursor.getColumnIndex(table.COLUMN_LOGIN))
        val userId = cursor.getString(cursor.getColumnIndex(table.COLUMN_USER_ID))
        val avatar_url = cursor.getString(cursor.getColumnIndex(table.COLUMN_AVATAR_URL))
        val name = cursor.getString(cursor.getColumnIndex(table.COLUMN_NAME))
        val company = cursor.getString(cursor.getColumnIndex(table.COLUMN_COMPANY))
        val blog = cursor.getString(cursor.getColumnIndex(table.COLUMN_BLOG))
        val location = cursor.getString(cursor.getColumnIndex(table.COLUMN_LOCATION))
        val email = cursor.getString(cursor.getColumnIndex(table.COLUMN_EMAIL))
        val created_at = cursor.getString(cursor.getColumnIndex(table.COLUMN_CREATED_AT))
        val updated_at = cursor.getString(cursor.getColumnIndex(table.COLUMN_UPDATED_AT))
        return User(login, userId, avatar_url, name, company, blog, location, email, created_at, updated_at)
    }

    fun getUserContentValue(table: UserTable, user: User): ContentValues {
        val values = ContentValues()
        values.put(table.COLUMN_LOGIN, user.login)
        values.put(table.COLUMN_USER_ID, user.id)
        values.put(table.COLUMN_AVATAR_URL, user.avatar_url)
        values.put(table.COLUMN_NAME, user.name)
        values.put(table.COLUMN_COMPANY, user.company)
        values.put(table.COLUMN_BLOG, user.blog)
        values.put(table.COLUMN_LOCATION, user.location)
        values.put(table.COLUMN_EMAIL, user.email)
        values.put(table.COLUMN_CREATED_AT, user.created_at)
        values.put(table.COLUMN_UPDATED_AT, user.updated_at)
        return values
    }

}
