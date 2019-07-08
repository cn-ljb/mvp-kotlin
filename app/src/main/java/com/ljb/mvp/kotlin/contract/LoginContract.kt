package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.User
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface LoginContract {

    interface IView : IViewContract {
        fun showLoadDialog()
        fun dismissLoadDialog()
        fun loginSuccess()
        fun loginError(errorMsg: String?)
        fun goHome()
    }

    interface IPresenter : IPresenterContract {
        fun login(userName: String)
        fun delayGoHomeTask()
        fun getLocLogin(): String?
    }

    interface IModel : IModelContract {
        fun delayGoHomeTask(): Observable<Long>
        fun getUserInfo(userName: String): Observable<User>
        fun saveLoginUser2SP(user: User): User
        fun saveUser2DB(user: User): Observable<Boolean>
        fun getLocLogin(): String?
    }
}