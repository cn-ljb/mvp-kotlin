package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.presenter.base.BaseRxLifePresenter

/**
 * Created by L on 2017/9/22.
 */
interface FollowersContract {

    interface IFollowersView : IBaseView {
        fun showPage(data: MutableList<Follower>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }


    abstract class IFollowersPresenter(mvpView: IFollowersView) : BaseRxLifePresenter<IFollowersView>(mvpView) {
        abstract fun onRefresh()
        abstract fun onLoadMore()
    }
}