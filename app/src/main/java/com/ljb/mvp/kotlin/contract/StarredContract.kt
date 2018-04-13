package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Starred
import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView

/**
 * Created by L on 2017/9/21.
 */
interface StarredContract{

    interface IStarredView : IBaseView {
        fun showPage(data: MutableList<Starred>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IStarredPresenter : IBasePresenter<IStarredView> {

        fun onRefresh()

        fun onLoadMore()
    }
}