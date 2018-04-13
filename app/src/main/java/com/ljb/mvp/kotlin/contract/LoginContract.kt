package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView

/**
 * Created by L on 2017/7/13.
 */
interface LoginContract {

    interface ILoginView : IBaseView {

        fun loginSuccess()

        fun loginError(errorMsg: String?)

        fun goHome()

        fun showLogin()

    }

    interface ILoginPresenter : IBasePresenter<ILoginView> {
        fun login(userName: String)
    }

}