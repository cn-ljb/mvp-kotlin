package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.rx.subscribeEx
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.model.LoginModel
import com.ljb.mvp.kotlin.table.UserTable
import com.ljb.mvp.kotlin.common.rx.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class LoginPresenter : BaseMvpPresenter<LoginContract.IView, LoginContract.IModel>(), LoginContract.IPresenter {

    override fun registerModel() = LoginModel::class.java

    override fun getLocLogin(): String? {
        return getModel().getLocLogin()
    }

    override fun delayGoHomeTask() {
        getModel().delayGoHomeTask()
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeEx {
                    onNextEx { getMvpView().goHome() }
                }
    }

    override fun login(userName: String) {
        getModel().getUserInfo(userName)
                .map { getModel().saveLoginUser2SP(it) }
                .flatMap { getModel().saveUser2DB(it) }
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .compose(RxUtils.schedulerIO2Main<Boolean>())
                .subscribeNet(getContextEx()) {
                    onSubscribeEx {
                        getMvpView().showLoadDialog()
                    }
                    onNextEx {
                        getMvpView().dismissLoadDialog()
                        getMvpView().loginSuccess()
                    }
                    onErrorEx {
                        getMvpView().dismissLoadDialog()
                        getMvpView().loginError(it.message)
                    }
                }
    }
}
