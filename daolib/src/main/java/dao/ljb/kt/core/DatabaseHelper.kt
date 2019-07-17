package dao.ljb.kt.core

import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Author:Ljb
 * Time:2018/12/7
 * There is a lot of misery in life
 **/
abstract class DatabaseHelper(val context: Context, val databaseName: String, val version: Int) {

    abstract fun onCreate(db: SQLiteDatabase)

    abstract fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
}