package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.contract.IPresenterContract
import com.ljb.mvp.contract.IViewContract
import com.ljb.mvp.kotlin.domain.User

/**
 * Created by L on 2017/7/18.
 */
interface MyContract {

    interface IView : IViewContract {
        fun logoutSuccess()
        fun showUserInfo(user: User)
    }

    interface IPresenter : IPresenterContract {
        fun logout()
        fun getUserInfo()
    }
}