package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView

/**
 * Created by L on 2017/9/22.
 */
interface FollowersContract {

    interface IFollowersView : IBaseView {
        fun showPage(data: MutableList<Follower>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }


    interface IFollowersPresenter : IBasePresenter<IFollowersView> {
        fun onRefresh()
        fun onLoadMore()
    }
}