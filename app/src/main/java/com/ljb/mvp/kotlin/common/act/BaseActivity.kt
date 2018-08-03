package com.ljb.mvp.kotlin.common.act

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle

abstract class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init(savedInstanceState)
        initView()
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected open fun init(savedInstanceState: Bundle?) {}

    protected open fun initView() {}

    protected open fun initData() {}

    override fun onBackPressed() {
        onBack()
    }

    protected open fun onBack() {
        super.onBackPressed()
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        if (res.configuration.fontScale != 1.0f) {
            val newConfig = Configuration()
            newConfig.setToDefaults()
            res.updateConfiguration(newConfig, res.displayMetrics)
        }
        return res
    }
}