package com.ljb.mvp.kotlin

import android.app.Application
import android.content.Context

/**
 * Created by L on 2017/7/14.
 */
class KotlinApplication : Application() {


    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

}