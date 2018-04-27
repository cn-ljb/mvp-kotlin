package com.ljb.mvp.kotlin.mvp.contract

/**
 * 公共Presenter层契约接口
 * Created by L on 2017/7/10.
 * */
interface IBasePresenterContract{
    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}