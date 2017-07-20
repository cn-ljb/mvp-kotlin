package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.User
import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

/**
 * Created by L on 2017/7/18.
 */
interface MyContract {

    interface IMyView : IBaseView {
        fun logoutSuccess()
        fun showUserInfo(user: User)
    }

    interface IMyPresenter : IBasePresenter<IMyView> {
        fun logout()
    }
}