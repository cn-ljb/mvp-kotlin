package com.ljb.mvp.kotlin.contract

import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import com.ljb.mvp.kotlin.domain.User

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
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