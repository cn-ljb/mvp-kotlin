package com.ljb.mvp.kotlin.contract

import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface LoginContract {

    interface IView : IViewContract {
        fun loginSuccess()
        fun loginError(errorMsg: String?)
        fun goHome()
    }

    interface IPresenter : IPresenterContract {
        fun login(userName: String)
        fun delayGoHomeTask()
    }
}