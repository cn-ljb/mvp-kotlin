package com.ljb.mvp.kotlin

import android.app.Application
import com.ljb.mvp.kotlin.common.Constant
import com.ljb.mvp.kotlin.common.Constant.GITHUB_CLIENT_ID
import com.ljb.mvp.kotlin.common.Constant.GITHUB_CLIENT_SECRET
import com.ljb.mvp.kotlin.common.Constant.HTTP_API_DOMAIN
import com.ljb.mvp.kotlin.db.DatabaseHelperImpl
import com.ljb.mvp.kotlin.protocol.dao.ProtocolConfig
import com.ljb.mvp.kotlin.utils.SPUtils
import com.squareup.leakcanary.LeakCanary
import com.tencent.bugly.crashreport.CrashReport
import dao.ljb.kt.DaoConfig
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
        //bugly
        CrashReport.initCrashReport(applicationContext, Constant.APP_ID_BUGLY, BuildConfig.DEBUG)
        //SharedPreferences
        SPUtils.init(this)
        //初始化网络库
        initNet()
        //初始化数据库
        initDB()
    }

    private fun initDB() {
        val dbHelper = DatabaseHelperImpl(this)
        val protocolConfig = ProtocolConfig()
        DaoConfig.init(dbHelper, protocolConfig)
    }

    private fun initNet() {
        val paramMap = mapOf(
                "client_id" to GITHUB_CLIENT_ID,
                "client_secret" to GITHUB_CLIENT_SECRET
        )
        HttpConfig.init(HTTP_API_DOMAIN, paramMap, paramMap, BuildConfig.DEBUG)
    }
}