package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.Constant
import com.ljb.mvp.kotlin.contract.MyContract
import com.wuba.weizhang.utils.SPUtils

/**
 * Created by L on 2017/7/18.
 */
class MyPresenter(private val mView: MyContract.IMyView) : MyContract.IMyPresenter {

    override fun getMvpView() = mView

    override fun startTask() {
    }


    override fun logout() {
        SPUtils.putLong(Constant.SPConstant.CUR_USER_ID, 0)
        getMvpView().logoutSuccess()
    }

    override fun onDestroy() {
    }

}