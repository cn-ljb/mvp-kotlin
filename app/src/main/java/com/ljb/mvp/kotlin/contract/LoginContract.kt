package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter

/**
 * Created by L on 2017/7/13.
 */
interface LoginContract {

    interface ILoginView : IBaseView {

        fun loginSuccess()

        fun loginError(errorMsg: String?)

        fun goHome()
    }

    abstract class ILoginPresenter : BaseRxLifePresenter<ILoginView>() {
        abstract fun login(userName: String)
        abstract fun delayGoHomeTask()
    }

}