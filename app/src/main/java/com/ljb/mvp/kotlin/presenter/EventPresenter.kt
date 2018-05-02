package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter
import com.ljb.mvp.kotlin.protocol.http.base.HttpFactory
import com.ljb.mvp.kotlin.protocol.http.IUserHttp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/9/14.
 */
class EventPresenter(mvpView: EventsContract.IView) : BaseRxLifePresenter<EventsContract.IView>(mvpView),
        EventsContract.IPresenter {

    private var mPage = 1

    override fun onLoadMore() {
        mPage++
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        HttpFactory.getProtocol(IUserHttp::class.java)
                .getEventsByName(LoginUser.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeEx(
                        { getMvpView().showPage(it, page) },
                        { getMvpView().errorPage(it, page) }
                ).bindRxLifeEx(RxLife.ON_DESTROY)
    }

}