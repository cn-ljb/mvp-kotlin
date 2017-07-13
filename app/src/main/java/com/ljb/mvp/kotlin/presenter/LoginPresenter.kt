package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.LoginContract

/**
 * Created by L on 2017/7/13.
 */
class LoginPresenter(private val mLoginView: LoginContract.ILoginView) : LoginContract.ILoginPresenter {

    override fun getMvpView() = mLoginView

    override fun startTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login(user: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}