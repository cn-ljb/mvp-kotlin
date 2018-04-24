package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter

/**
 * Created by L on 2017/9/27.
 */
interface RepositoriesContract {

    interface IRepositoriesView : IBaseView {
        fun showPage(data: MutableList<Repository>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    abstract class IRepositoriesPresenter(mvpView: IRepositoriesView) : BaseRxLifePresenter<IRepositoriesView>(mvpView) {

        abstract fun onRefresh()

        abstract fun onLoadMore()
    }

}