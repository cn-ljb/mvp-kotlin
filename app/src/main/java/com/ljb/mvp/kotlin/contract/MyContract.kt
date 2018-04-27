package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.mvp.contract.IBasePresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IBaseViewContract

/**
 * Created by L on 2017/7/18.
 */
interface MyContract {

    interface IMyView : IBaseViewContract {
        fun logoutSuccess()
        fun showUserInfo(user: User)
    }

    interface IMyPresenter : IBasePresenterContract {
        fun logout()
        fun getUserInfo()
    }
}