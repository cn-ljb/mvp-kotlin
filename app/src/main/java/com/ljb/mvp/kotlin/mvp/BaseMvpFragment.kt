package com.ljb.mvp.kotlin.mvp

import android.support.v4.app.Fragment

/**
 * Created by L on 2017/7/10.
 */
abstract class BaseMvpFragment<out T : IBasePresenter<*>> : Fragment() {

    protected val mPresenter: T by lazy { createPresenter() }

    protected abstract fun createPresenter(): T

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
