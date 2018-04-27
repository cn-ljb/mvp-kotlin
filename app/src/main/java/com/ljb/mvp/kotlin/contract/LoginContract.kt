package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.mvp.contract.IBasePresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IBaseViewContract

/**
 * 登录页View层\Presenter层通讯契约接口
 * Created by L on 2017/7/13.
 */
interface LoginContract {

    interface IView : IBaseViewContract {
        fun loginSuccess()
        fun loginError(errorMsg: String?)
        fun goHome()
    }

    interface IPresenter : IBasePresenterContract {
        fun login(userName: String)
        fun delayGoHomeTask()
    }
}