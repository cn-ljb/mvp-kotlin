package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.common.rx.RxUtils
import com.ljb.mvp.kotlin.common.rx.subscribeNet
import com.ljb.mvp.kotlin.contract.RepositoriesContract
import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.model.RepositoriesModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class RepositoriesPresenter : BaseMvpPresenter<RepositoriesContract.IView, RepositoriesContract.IModel>(), RepositoriesContract.IPresenter {

    private var mPage = 1

    override fun registerModel() = RepositoriesModel::class.java

    override fun onLoadMore() {
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        getModel().getRepositories(page)
                .compose(RxUtils.bindToLifecycle(getMvpView()))
                .compose(RxUtils.schedulerIO2Main<MutableList<Repository>>())
                .subscribeNet(getContextEx()) {
                    onNextEx {
                        getMvpView().showPage(it, page)
                        mPage++
                    }
                    onErrorEx { getMvpView().errorPage(it, page) }
                }
    }

}