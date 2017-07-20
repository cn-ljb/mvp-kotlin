package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.protocol.dao.UsersDaoProtocol
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.common.LoginUser
import com.wuba.weizhang.mvp.getContext
import com.wuba.weizhang.protocol.http.UsersProtocol
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/7/13.
 */
class LoginPresenter(private val mView: LoginContract.ILoginView) : LoginContract.ILoginPresenter {

    override fun getMvpView() = mView

    val mUsersProtocol by lazy { UsersProtocol() }
    val mUsersDaoProtocol by lazy { UsersDaoProtocol(getContext()) }
    val mTimerObservable: Observable<Long> by lazy { Observable.timer(1500, TimeUnit.MILLISECONDS) }

    var mTimerDisposable: Disposable? = null
    var mLoginDisposable: Disposable? = null

    override fun startTask() {
        if (LoginUser.name.isNullOrBlank()) {
            mView.showLogin()
        } else {
            mTimerDisposable = mTimerObservable.subscribe { mView.goHome() }
        }
    }

    override fun login(userName: String) {
        mLoginDisposable = mUsersProtocol.getUserInfoByName(userName)
                .map {
                    if (it.message.isNullOrBlank()) {
                        if (mUsersDaoProtocol.findUserByUserId(it.id) == null) {
                            mUsersDaoProtocol.saveUser(it)
                        } else {
                            mUsersDaoProtocol.updateUser(it)
                        }
                    }
                    it
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.message.isNullOrBlank()) {
                        LoginUser.name = it.login
                        getMvpView().loginSuccess()
                    } else {
                        getMvpView().loginError(it.message)
                    }
                }, {
                    getMvpView().loginError(null)
                })
    }

    override fun onDestroy() {
        RxUtils.dispose(mTimerDisposable)
        RxUtils.dispose(mLoginDisposable)
    }

}