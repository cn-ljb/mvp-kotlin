package com.ljb.mvp.kotlin.contract.base

import com.ljb.mvp.kotlin.mvp.contract.IBasePresenterContract
import com.ljb.mvp.kotlin.mvp.contract.IBaseViewContract


interface ListContract {

    interface IView<T> : IBaseViewContract {
        fun showPage(data: MutableList<T>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IPresenter : IBasePresenterContract {
        fun onRefresh()
        fun onLoadMore()
    }

}

