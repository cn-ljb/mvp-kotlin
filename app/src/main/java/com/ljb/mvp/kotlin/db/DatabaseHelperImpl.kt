package com.ljb.mvp.kotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ljb.mvp.kotlin.common.Constant
import com.ljb.mvp.kotlin.table.UserTable
import com.ljb.mvp.kotlin.utils.XLog
import dao.ljb.kt.core.DatabaseHelper

/**
 * Author:Ljb
 * Time:2018/12/7
 * There is a lot of misery in life
 **/
class DatabaseHelperImpl(context: Context) : DatabaseHelper(context, Constant.DB.NAME, Constant.DB.VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        //数据库创建时，即可初始化用户信息表
        val userTable = UserTable()
        val sql = DatabaseSqlHelper.getCreateTableSql(userTable)
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
