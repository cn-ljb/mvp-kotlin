package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Following
import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter

/**
 * Created by L on 2017/10/9.
 */
interface FollowingContract {

    interface IFollowingView : IBaseView {
        fun showPage(data: MutableList<Following>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    abstract class IFollowingPresenter(mvpView: IFollowingView) : BaseRxLifePresenter<IFollowingView>(mvpView) {

        abstract fun onRefresh()

        abstract fun onLoadMore()
    }
}