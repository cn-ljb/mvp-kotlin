package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.rx.RxUtils
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.FollowersContract
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.model.FollowersModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class FollowersPresenter : BaseMvpPresenter<FollowersContract.IView, FollowersContract.IModel>(), FollowersContract.IPresenter {

    private var mPage = 1

    override fun registerModel() = FollowersModel::class.java

    override fun onLoadMore() {
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        getModel().getFollowers(page)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .compose(RxUtils.schedulerIO2Main<MutableList<Follower>>())
                .subscribeNet(getContextEx()) {
                    onNextEx {
                        getMvpView().showPage(it, page)
                        mPage++
                    }
                    onErrorEx { getMvpView().errorPage(it, page) }
                }
    }
}