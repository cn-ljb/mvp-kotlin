package com.ljb.mvp.kotlin

import android.app.Application
import com.ljb.mvp.kotlin.common.Constant
import com.ljb.mvp.kotlin.common.GITHUB_CLIENT_ID
import com.ljb.mvp.kotlin.common.GITHUB_CLIENT_SECRET
import com.ljb.mvp.kotlin.common.HTTP_API_DOMAIN
import com.ljb.mvp.kotlin.utils.SPUtils
import com.squareup.leakcanary.LeakCanary
import net.ljb.kt.HttpConfig

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
        initNet()
    }

    private fun initNet() {
        val paramMap = mapOf(
                "client_id" to GITHUB_CLIENT_ID,
                "client_secret" to GITHUB_CLIENT_SECRET)
        HttpConfig.init(HTTP_API_DOMAIN, paramMap, paramMap, true)
    }
}