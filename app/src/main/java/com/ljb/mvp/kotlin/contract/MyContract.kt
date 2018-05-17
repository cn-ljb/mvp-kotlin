package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.mvp.contract.IPresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IViewContract

/**
 * Created by L on 2017/7/18.
 */
interface MyContract {

    interface IMyView : IViewContract {
        fun logoutSuccess()
        fun showUserInfo(user: User)
    }

    interface IMyPresenter : IPresenterContract {
        fun logout()
        fun getUserInfo()
    }
}