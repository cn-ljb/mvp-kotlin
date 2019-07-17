package dao.ljb.kt.db

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dao.ljb.kt.DaoConfig

/**
 * Author:Ljb
 * Time:2018/11/8
 * There is a lot of misery in life
 **/

class DatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, DaoConfig.getHelper().databaseName, null, DaoConfig.getHelper().version) {

    //单例
    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: DatabaseOpenHelper? = null

        fun getDefInstance(): DatabaseOpenHelper {
            if (instance != null) return instance as DatabaseOpenHelper
            return if (instance != null) {
                instance as DatabaseOpenHelper
            } else {
                instance = DatabaseOpenHelper(DaoConfig.getHelper().context)
                instance as DatabaseOpenHelper
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        DaoConfig.getHelper().onCreate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        DaoConfig.getHelper().onUpgrade(db, oldVersion, newVersion)
    }

}