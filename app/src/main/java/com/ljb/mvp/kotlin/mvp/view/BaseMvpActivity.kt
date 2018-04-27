package com.ljb.mvp.kotlin.mvp.view

import android.app.Activity
import android.os.Bundle
import com.ljb.mvp.kotlin.mvp.contract.IBasePresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IBaseViewContract


abstract class BaseMvpActivity<T : IBasePresenterContract> : Activity(), IBaseViewContract {

    protected val mPresenter: T by lazy { createPresenter() }

    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
