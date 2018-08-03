package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.common.ex.subscribeEx
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter
import com.ljb.mvp.kotlin.protocol.dao.IUserDao
import com.ljb.mvp.kotlin.protocol.dao.base.DaoFactory
import com.ljb.mvp.kotlin.protocol.dao.impl.UserDaoProtocol
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import com.ljb.mvp.presenter.getContextEx
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.ljb.kt.client.HttpFactory

/**
 * Created by L on 2017/7/18.
 */
class MyPresenter : BaseRxLifePresenter<MyContract.IView>(),
        MyContract.IPresenter {

    override fun getUserInfo() {
        Observable.concat(
                UserDaoProtocol.createObservable {
                    DaoFactory.getProtocol(IUserDao::class.java).findUserByName(getContextEx(), LoginUser.name)
                },
                HttpFactory.getProtocol(IUserHttpProtocol::class.java).getUserInfoByName(LoginUser.name)
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