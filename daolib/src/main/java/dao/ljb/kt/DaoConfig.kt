package dao.ljb.kt

import android.annotation.SuppressLint
import dao.ljb.kt.core.DatabaseHelper
import dao.ljb.kt.core.IDaoProtocolConfig

/**
 * Author:Ljb
 * Time:2018/12/7
 * There is a lot of misery in life
 **/
object DaoConfig {

    @SuppressLint("StaticFieldLeak")
    private var mDbHelper: DatabaseHelper? = null
    private var mTransform: IDaoProtocolConfig? = null

    fun init(helper: DatabaseHelper, protocolTransform: IDaoProtocolConfig) {
        mDbHelper = helper
        mTransform = protocolTransform
    }

    fun getHelper(): DatabaseHelper {
        if (mDbHelper == null) throw IllegalStateException("dao not init")
        return mDbHelper!!
    }

    fun getTransform(): IDaoProtocolConfig {
        if (mTransform == null) throw IllegalStateException("dao not init")
        return mTransform!!
    }

}
