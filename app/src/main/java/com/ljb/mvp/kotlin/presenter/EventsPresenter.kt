package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.protocol.http.EventProtocol
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.common.LoginUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/9/14.
 */
class EventsPresenter(private val mView: EventsContract.IEventsView) : EventsContract.IEventsPresenter {

    override fun getMvpView(): EventsContract.IEventsView = mView

    private var mEventDisposable: Disposable? = null

    override fun startTask() {
        mEventDisposable = EventProtocol.getEventsByName(LoginUser.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { getMvpView().showEvents(it) },
                        { it.printStackTrace() }
                )
    }

    override fun onDestroy() {
        RxUtils.dispose(mEventDisposable)
    }
}