package com.wuba.weizhang.mvp

/**
 * Created by L on 2017/7/10.
 */
interface IBasePresenter<out V : IBaseView> {

    fun getMvpView() : V

    fun startTask()

    fun onDestroy()
}
