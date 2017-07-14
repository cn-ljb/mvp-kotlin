package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.Constant
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.utils.SPUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/7/13.
 */
class LoginPresenter(private val mLoginView: LoginContract.ILoginView) : LoginContract.ILoginPresenter {

    override fun getMvpView() = mLoginView

    val mTimerObservable: Observable<Long> by lazy { Observable.timer(3000, TimeUnit.MILLISECONDS) }
    val mIsLoggedObservable: Observable<Boolean> by lazy {
        Observable.create<Boolean> {
            it.onNext(!SPUtils.getString(Constant.SPConstant.USER_ID).isNullOrEmpty())
            it.onComplete()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    lateinit var mTimerDisposable: Disposable
    lateinit var mIsLoggedDisposable: Disposable

    override fun startTask() {
        mTimerDisposable = mTimerObservable.subscribe { mLoginView.goHome() }
        mIsLoggedDisposable = mIsLoggedObservable.subscribe {
            if (it) {  //已登录
                mLoginView.goHome()
            } else {  //未登录
                RxUtils.dispose(mTimerDisposable)
                mLoginView.showLogin()
            }
        }

    }

    override fun login(user: String) {
        mLoginView.loginSuccess()
    }

    override fun onDestroy() {
        RxUtils.dispose(mTimerDisposable)
        RxUtils.dispose(mIsLoggedDisposable)
    }

}