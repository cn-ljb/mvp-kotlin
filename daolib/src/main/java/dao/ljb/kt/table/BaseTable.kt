package dao.ljb.kt.table

import android.text.TextUtils

/**
 * Author:Ljb
 * Time:2018/11/8
 * There is a lot of misery in life
 **/

abstract class BaseTable {

    companion object {
        const val TYPE_TEXT = "TEXT"
        const val TYPE_LONG = "LONG"
        const val TYPE_INTEGER = "INTEGER"
    }

    private var mTableName = ""
    private var mColumnsMap: Map<String, String>? = null

    protected abstract fun createTableName(): String

    protected abstract fun createColumns(): Map<String, String>

    fun getName(): String {
        if (TextUtils.isEmpty(mTableName)) {
            val tempName = createTableName()
            if (TextUtils.isEmpty(tempName)) throw  IllegalStateException("table name is empty or null")
            mTableName = tempName
        }
        return mTableName
    }

    fun getColumns(): Map<String, String> {
        if (mColumnsMap == null || mColumnsMap!!.isEmpty()) {
            val tempMap = createColumns()
            if (tempMap.isEmpty()) throw  IllegalStateException("table columns is empty or null")
            mColumnsMap = tempMap
        }
        return mColumnsMap!!
    }


}