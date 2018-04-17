package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter

/**
 * Created by L on 2017/7/18.
 */
interface MyContract {

    interface IMyView : IBaseView {
        fun logoutSuccess()
        fun showUserInfo(user: User)
    }

    abstract class IMyPresenter(mvpView: IMyView) : BaseRxLifePresenter<IMyView>(mvpView) {
        abstract fun logout()
        abstract fun getUserInfo()
    }
}