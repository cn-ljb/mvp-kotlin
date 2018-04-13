package com.ljb.mvp.kotlin

import android.app.Application
import com.ljb.mvp.kotlin.utils.SPUtils

/**
 * Created by L on 2017/7/14.
 */
class KotlinMVPApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        SPUtils.initInstance(this)
    }
}