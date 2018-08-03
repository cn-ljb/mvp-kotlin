package com.ljb.mvp.presenter

import com.ljb.mvp.contract.IViewContract

/**
 * Created by L on 2017/7/10.
 */
interface IBasePresenter<out V : IViewContract> {
    fun getMvpView(): V
}




