package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.mvp.presenter.getContext
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter
import com.ljb.mvp.kotlin.protocol.dao.UserDaoProtocol
import com.ljb.mvp.kotlin.utils.RxUtils
import com.ljb.mvp.kotlin.protocol.http.UserProtocol
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 1、继承BaseRxLifePresenter
 * 2、通过泛型告诉Presenter层，当前View使用的通讯契约
 * 3、实现自身的通讯契约
 */
class LoginPresenter(mvpView: LoginContract.IView) : BaseRxLifePresenter<LoginContract.IView>(mvpView), LoginContract.IPresenter {

    private var mLoginDisposable: Disposable? = null

    override fun delayGoHomeTask() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .subscribe { getMvpView().goHome() }
                .bindRxLifeEx(RxLife.ON_DESTROY)
    }

    override fun login(userName: String) {
        RxUtils.dispose(mLoginDisposable)
        mLoginDisposable = UserProtocol.getUserInfoByName(userName)
                .map {
                    if (it.message.isNullOrBlank()) {
                        if (UserDaoProtocol.findUserByUserId(getContext(), it.id) == null) {
                            UserDaoProtocol.saveUser(getContext(), it)
                        } else {
                            UserDaoProtocol.updateUser(getContext(), it)
                        }
                    }
                    it
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeEx({
                    if (it.message.isNullOrBlank()) {
                        LoginUser.name = it.login
                        getMvpView().loginSuccess()
                    } else {
                        getMvpView().loginError(it.message)
                    }
                }, {
                    getMvpView().loginError(null)
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

}