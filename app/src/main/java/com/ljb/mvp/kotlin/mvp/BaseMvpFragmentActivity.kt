package com.wuba.weizhang.mvp

import android.support.v4.app.FragmentActivity

/**
 * Created by L on 2017/7/10.
 */
abstract class BaseMvpFragmentActivity<out T : IBasePresenter<out IBaseView>> : FragmentActivity() {

    val mPresenter: T by lazy { createPresenter() }

    protected abstract fun createPresenter(): T

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}