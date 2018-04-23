package com.ljb.mvp.kotlin.mvp

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by L on 2017/7/10.
 */
interface IBasePresenter<out V : IBaseView> {

    fun getMvpView(): V

    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}

fun IBasePresenter<IBaseView>.getContext(): Context = when {
    getMvpView() is Activity -> getMvpView() as Activity
    getMvpView() is Fragment -> (getMvpView() as Fragment).activity
    else -> throw IllegalStateException("the presenter not found context")
}





