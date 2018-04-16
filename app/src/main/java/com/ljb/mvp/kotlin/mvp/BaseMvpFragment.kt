package com.ljb.mvp.kotlin.mvp

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by L on 2017/7/10.
 */
abstract class BaseMvpFragment<out T : IBasePresenter<*>> : Fragment() {

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
