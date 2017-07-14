package com.ljb.mvp.kotlin.contract

import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

/**
 * Created by L on 2017/7/13.
 */
interface LoginContract {

    interface ILoginView : IBaseView {

        fun loginSuccess()

        fun loginError(msg: String)

        fun goHome()
        
        fun showLogin()

    }

    interface ILoginPresenter : IBasePresenter<ILoginView> {

        fun login(user: String)
    }

}