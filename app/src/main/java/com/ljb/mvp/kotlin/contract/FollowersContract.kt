package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Follower
import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

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