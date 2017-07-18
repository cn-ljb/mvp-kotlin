package com.ljb.mvp.kotlin.contract

import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

/**
 * Created by L on 2017/7/18.
 */
interface MyContract {

    interface IMyView : IBaseView {
        fun logoutSuccess()
    }

    interface IMyPresenter : IBasePresenter<IMyView> {
        fun logout()
    }
}