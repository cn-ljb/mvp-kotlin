package com.ljb.mvp.kotlin.mvp.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.ljb.mvp.kotlin.mvp.contract.IPresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IViewContract

/**
 * Created by L on 2017/7/10.
 */
abstract class BaseMvpFragmentActivity<out P : IPresenterContract> : FragmentActivity(), IBaseView<P>, IViewContract {

    private val mPresenter: P by lazy { createPresenter() }

    fun getPresenter() = mPresenter

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