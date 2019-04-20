package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.common.rx.subscribeEx
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.dao.IUserDaoProtocol
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import com.ljb.mvp.kotlin.table.UserTable
import com.ljb.mvp.kotlin.utils.RxUtils
import dao.ljb.kt.core.DaoFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx
import net.ljb.kt.client.HttpFactory
import java.util.concurrent.TimeUnit

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class LoginPresenter : BaseMvpPresenter<LoginContract.IView>(), LoginContract.IPresenter {

    private val mUserTable = UserTable()

    override fun delayGoHomeTask() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .subscribeEx {
                    onNextEx { getMvpView().goHome() }
                }
    }

    override fun login(userName: String) {
        HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getUserInfoByName(userName)
                .map { saveLoginUser2sp(it) }
                .flatMap { DaoFactory.getProtocol(IUserDaoProtocol::class.java).saveUser(mUserTable, it) }
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNet(getContextEx()) {
                    onNextEx {
                        getMvpView().loginSuccess()
                    }
                    onErrorEx {
                        getMvpView().loginError(it.message)
                    }
                }
    }

    private fun saveLoginUser2sp(user: User): User {
        LoginUser.login = user.login
        LoginUser.uid = user.id
        LoginUser.name = user.name ?: ""
        LoginUser.img = user.avatar_url
        return user
    }
}
