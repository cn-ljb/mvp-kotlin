package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.common.LoginUser
import com.wuba.weizhang.protocol.http.UserProtocol
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/9/14.
 */
class EventPresenter(mvpView: EventsContract.IEventsView) : EventsContract.IEventsPresenter(mvpView) {

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
        UserProtocol.getEventsByName(LoginUser.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { getMvpView().showPage(it, page) },
                        { getMvpView().errorPage(it, page) }
                ).bindRxLife(RxLife.ON_DESTROY)
    }

}