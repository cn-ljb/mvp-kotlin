package com.ljb.mvp.kotlin

import android.app.Application
import com.ljb.mvp.kotlin.utils.SPUtils
import com.squareup.leakcanary.LeakCanary

/**
 * Created by L on 2017/7/14.
 */
class KotlinMVPApplication : Application() {

    //应避免创建全局的Application引用
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        SPUtils.init(this)
    }
}