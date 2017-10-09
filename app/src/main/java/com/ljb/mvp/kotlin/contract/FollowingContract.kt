package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Following
import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

/**
 * Created by L on 2017/10/9.
 */
interface FollowingContract {

    interface IFollowingView : IBaseView {
        fun showPage(data: MutableList<Following>, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IFollowingPresenter : IBasePresenter<IFollowingView> {

        fun onRefresh()

        fun onLoadMore()
    }
}