package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.common.LoginUser
import com.wuba.weizhang.protocol.http.UserProtocol
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/9/14.
 */
class EventPresenter(private val mView: EventsContract.IEventsView) : EventsContract.IEventsPresenter {

    private var mPage = 1

    override fun getMvpView(): EventsContract.IEventsView = mView

    private var mEventDisposable: Disposable? = null

    override fun startTask() {
        onRefresh()
    }

    override fun onLoadMore() {
        mPage++
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        mEventDisposable = UserProtocol.getEventsByName(LoginUser.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { getMvpView().showPage(it, page) },
                        { getMvpView().errorPage(it, page) }
                )
    }

    override fun onDestroy() {
        RxUtils.dispose(mEventDisposable)
    }
}