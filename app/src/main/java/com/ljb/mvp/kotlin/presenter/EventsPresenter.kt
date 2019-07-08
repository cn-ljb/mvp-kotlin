package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.protocol.http.IReposHttpProtocol
import com.ljb.mvp.kotlin.common.rx.RxUtils
import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.model.EventsModel
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
class EventsPresenter : BaseMvpPresenter<EventsContract.IView, EventsContract.IModel>(), EventsContract.IPresenter {

    override fun registerModel() = EventsModel::class.java

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
        getModel().getEvents(page)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .compose(RxUtils.schedulerIO2Main<MutableList<Event>>())
                .subscribeNet(getContextEx()) {
                    onNextEx { getMvpView().showPage(it, page) }
                    onErrorEx { getMvpView().errorPage(it, page) }
                }
    }

    override fun getReposFromUrl(url: String) {
        HttpFactory.getProtocol(IReposHttpProtocol::class.java)
                .getReposFromUrl(url)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNet(getContextEx()) {
                    onNextEx { getMvpView().setRepos(it) }
                    onErrorEx { getMvpView().setRepos(null) }
                }
    }

}