package com.ljb.mvp.kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.ljb.mvp.kotlin.BuildConfig

/**
 * Author:Ljb
 * Time:2018/12/28
 * There is a lot of misery in life
 **/
object SPUtils {

    private const val SP_NAME = "sp_${BuildConfig.APPLICATION_ID}"

    private var instance: SharedPreferences? = null

    /** 使用前请先初始化 */
    fun init(context: Context) {
        if (instance != null) return
        instance = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    private fun checkInstance() {
        if (instance == null) throw InstantiationException("not init SharedPreferences ")
    }

    fun putString(key: String, value: String) {
        checkInstance()
        instance!!.edit().putString(key, value).apply()
    }

    @SuppressLint("ApplySharedPref")
    fun putStringCommit(key: String, value: String) {
        checkInstance()
        instance!!.edit().putString(key, value).commit()
    }


    fun getString(key: String, def: String = ""): String {
        checkInstance()
        return instance!!.getString(key, def)
    }

    fun putBoolean(key: String, value: Boolean) {
        checkInstance()
        instance!!.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, def: Boolean = false): Boolean {
        return instance!!.getBoolean(key, def)
    }

    fun putLong(key: String, value: Long) {
        checkInstance()
        instance!!.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, def: Long = 0L): Long {
        checkInstance()
        return instance!!.getLong(key, def)
    }

    fun putInt(key: String, value: Int) {
        checkInstance()
        instance!!.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, def: Int = 0): Int {
        checkInstance()
        return instance!!.getInt(key, def)
    }

    fun putFloat(key: String, value: Float) {
        checkInstance()
        instance!!.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String, def: Float = 0f): Float {
        checkInstance()
        return instance!!.getFloat(key, def)
    }

    fun putStringSet(key: String, setValue: Set<String>) {
        checkInstance()
        instance!!.edit().putStringSet(key, setValue).apply()
    }

    fun getStringSet(key: String, def: Set<String>? = null): Set<String>? {
        checkInstance()
        return instance!!.getStringSet(key, def)
    }

}
