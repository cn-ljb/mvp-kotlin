package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.MyContract

/**
 * Created by L on 2017/7/18.
 */
class MyPresenter(private val mView: MyContract.IMyView) : MyContract.IMyPresenter {

    override fun getMvpView() = mView

    override fun startTask() {
    }

    override fun onDestroy() {
    }

}