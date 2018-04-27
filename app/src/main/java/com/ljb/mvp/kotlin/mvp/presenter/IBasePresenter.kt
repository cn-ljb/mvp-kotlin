package com.ljb.mvp.kotlin.mvp.presenter

import com.ljb.mvp.kotlin.mvp.contract.IBaseViewContract

/**
 * Created by L on 2017/7/10.
 */
interface IBasePresenter<out V : IBaseViewContract> {

    fun getMvpView(): V
}






