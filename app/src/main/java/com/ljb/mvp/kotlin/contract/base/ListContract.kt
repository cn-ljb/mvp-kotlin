package com.ljb.mvp.kotlin.contract.base

import com.ljb.mvp.kotlin.mvp.contract.IPresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IViewContract


interface ListContract {

    interface IView<T> : IViewContract {
        fun showPage(data: MutableList<T>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IPresenter : IPresenterContract {
        fun onRefresh()
        fun onLoadMore()
    }

}

