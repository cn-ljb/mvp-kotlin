package com.wuba.weizhang.utils


import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import com.ljb.mvp.kotlin.KotlinApplication


object SPUtils {

    private val SP_NAME = "sp_58che"

    val instance: SharedPreferences
        get() = KotlinApplication.mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        instance.edit().putString(key, value).apply()
    }

    /**
     * SP中读取key对应的boolean型value

     * @param key
     * *
     * @return
     */
    fun getString(key: String): String {
        return instance.getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean) {
        instance.edit().putBoolean(key, value).apply()
    }

    /**
     * SP中读取key对应的boolean型value

     * @param key
     * *
     * @return 没有返回false
     */
    fun getBoolean(key: String): Boolean {
        return instance.getBoolean(key, false)
    }

    fun putLong(key: String, value: Long) {
        instance.edit().putLong(key, value).apply()
    }

    /**
     * SP中读取key对应的整形value

     * @param key
     * *
     * @return 没有返回0
     */
    fun getLong(key: String): Long {
        return instance.getLong(key, 0L)
    }

    fun putInt(key: String, value: Int) {
        instance.edit().putInt(key, value).apply()
    }

    /**
     * SP中读取key对应的整形value

     * @param key
     * *
     * @return 没有返回0
     */
    fun getInt(key: String): Int {
        return instance.getInt(key, 0)
    }

    fun putFloat(key: String, value: Float) {
        instance.edit().putFloat(key, value).apply()
    }

    /**
     * SP中读取key对应的整形value

     * @param key
     * *
     * @return 没有返回0
     */
    fun getFloat(key: String): Float {
        return instance.getFloat(key, 0f)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun putStringSet(key: String, setValue: Set<String>) {
        instance.edit().putStringSet(key, setValue).apply()
    }

    /**
     * SP中读取key对应的String类型set集合

     * @param key
     * *
     * @return 没有返回null
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun getStringSet(key: String): Set<String> {
        return instance.getStringSet(key, null)
    }

}
