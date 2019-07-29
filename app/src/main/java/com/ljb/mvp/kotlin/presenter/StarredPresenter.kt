package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.rx.RxUtils
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.StarredContract
import com.ljb.mvp.kotlin.domain.Starred
import com.ljb.mvp.kotlin.model.StarredModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class StarredPresenter : BaseMvpPresenter<StarredContract.IView, StarredContract.IModel>(), StarredContract.IPresenter {

    override fun registerModel() = StarredModel::class.java

    private var mPage = 1

    override fun onLoadMore() {
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        getModel().getStarred(page)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .compose(RxUtils.schedulerIO2Main<MutableList<Starred>>())
                .subscribeNet(getContextEx()) {
                    onNextEx {
                        getMvpView().showPage(it, page)
                        mPage++
                    }
                    onErrorEx { getMvpView().errorPage(it, page) }
                }
    }
}