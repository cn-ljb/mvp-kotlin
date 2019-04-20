package com.ljb.mvp.kotlin.table

import android.provider.BaseColumns
import dao.ljb.kt.table.BaseTable

/**
 * Author:Ljb
 * Time:2019/4/20
 * There is a lot of misery in life
 **/
class UserTable : BaseTable() {

    val COLUMN_ID = BaseColumns._ID
    val COLUMN_LOGIN = "login"
    val COLUMN_USER_ID = "user_id"
    val COLUMN_AVATAR_URL = "avatar_url"
    val COLUMN_NAME = "name"
    val COLUMN_COMPANY = "company"
    val COLUMN_BLOG = "blog"
    val COLUMN_LOCATION = "location"
    val COLUMN_EMAIL = "email"
    val COLUMN_CREATED_AT = "created_at"
    val COLUMN_UPDATED_AT = "updated_at"

    override fun createTableName() = "tb_user"

    override fun createColumns(): Map<String, String> {
        val tableColumns = HashMap<String, String>()
        tableColumns[COLUMN_ID] = "integer primary key autoincrement"
        tableColumns[COLUMN_LOGIN] = TYPE_TEXT
        tableColumns[COLUMN_USER_ID] = TYPE_TEXT
        tableColumns[COLUMN_AVATAR_URL] = TYPE_TEXT
        tableColumns[COLUMN_NAME] = TYPE_TEXT
        tableColumns[COLUMN_COMPANY] = TYPE_TEXT
        tableColumns[COLUMN_BLOG] = TYPE_TEXT
        tableColumns[COLUMN_LOCATION] = TYPE_TEXT
        tableColumns[COLUMN_EMAIL] = TYPE_TEXT
        tableColumns[COLUMN_CREATED_AT] = TYPE_TEXT
        tableColumns[COLUMN_UPDATED_AT] = TYPE_TEXT
        return tableColumns
    }

}
