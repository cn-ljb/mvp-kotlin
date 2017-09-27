package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Repository
import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

/**
 * Created by L on 2017/9/27.
 */
interface RepositoriesContract{

    interface IRepositoriesView : IBaseView {
        fun showPage(data: MutableList<Repository>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IRepositoriesPresenter : IBasePresenter<IRepositoriesView> {

        fun onRefresh()

        fun onLoadMore()
    }

}