package com.ljb.mvp.kotlin.mvp

import android.content.Context
import android.support.v4.app.Fragment
import com.ljb.mvp.kotlin.KotlinMVPApplication

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
    getMvpView() is Context -> getMvpView() as Context
    getMvpView() is Fragment -> (getMvpView() as Fragment).context
    else -> throw IllegalStateException("The presenter not found Context")
}




