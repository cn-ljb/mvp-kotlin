package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.rx.RxUtils
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.FollowingContract
import com.ljb.mvp.kotlin.model.FollowingModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class FollowingPresenter : BaseMvpPresenter<FollowingContract.IView, FollowingContract.IModel>(), FollowingContract.IPresenter {

    override fun registerModel() = FollowingModel::class.java

    private var mPage = 1

    override fun onLoadMore() {
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        getModel().getFollowing(page)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .compose(RxUtils.schedulerIO2Main())
                .subscribeNet(getContextEx()) {
                    onNextEx {
                        getMvpView().showPage(it, page)
                        mPage++
                    }
                    onErrorEx { getMvpView().errorPage(it, page) }
                }
    }

}