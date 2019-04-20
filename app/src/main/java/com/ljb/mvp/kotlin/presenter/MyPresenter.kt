package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.MyContract
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

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class MyPresenter : BaseMvpPresenter<MyContract.IView>(), MyContract.IPresenter {

    private val mUserTable = UserTable()

    override fun getUserInfo() {
        Observable.concat(
                DaoFactory.getProtocol(IUserDaoProtocol::class.java).queryUserByUserId(mUserTable, LoginUser.uid),
                HttpFactory.getProtocol(IUserHttpProtocol::class.java).getUserInfoByName(LoginUser.login)
        ).filter { User.EMPTY != it }
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNet(getContextEx()) {
                    onNextEx { getMvpView().showUserInfo(it!!) }
                }
    }


    override fun logout() {
        LoginUser.clear()
        getMvpView().logoutSuccess()
    }

}