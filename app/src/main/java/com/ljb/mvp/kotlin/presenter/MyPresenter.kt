package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.mvp.presenter.getContext
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter
import com.ljb.mvp.kotlin.protocol.dao.IUserDao
import com.ljb.mvp.kotlin.protocol.dao.base.DaoFactory
import com.ljb.mvp.kotlin.protocol.http.base.HttpFactory
import com.ljb.mvp.kotlin.protocol.dao.impl.UserDaoProtocol
import com.ljb.mvp.kotlin.protocol.http.IUserHttp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/7/18.
 */
class MyPresenter(mvpView: MyContract.IMyView) : BaseRxLifePresenter<MyContract.IMyView>(mvpView),
        MyContract.IMyPresenter {

    override fun getUserInfo() {
        Observable.concat(
                UserDaoProtocol.createObservable {
                    DaoFactory.getProtocol(IUserDao::class.java).findUserByName(getContext(), LoginUser.name)
                },
                HttpFactory.getProtocol(IUserHttp::class.java).getUserInfoByName(LoginUser.name)
        ).filter { it != null }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeEx({ getMvpView().showUserInfo(it!!) })
                .bindRxLifeEx(RxLife.ON_DESTROY)
    }


    override fun logout() {
        LoginUser.name = ""
        getMvpView().logoutSuccess()
    }

}