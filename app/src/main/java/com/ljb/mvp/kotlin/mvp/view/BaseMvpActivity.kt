package com.ljb.mvp.kotlin.mvp.view

import android.app.Activity
import android.os.Bundle
import com.ljb.mvp.kotlin.mvp.contract.IPresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IViewContract


abstract class BaseMvpActivity<out P : IPresenterContract> : Activity(), IBaseView<P>, IViewContract {

    private val mPresenter: P by lazy {
        val clazz = registerPresenter()
        val constructor = clazz.getConstructor()
        val presenter = constructor.newInstance()
        presenter.registerMvpView(this)
        presenter
    }

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
