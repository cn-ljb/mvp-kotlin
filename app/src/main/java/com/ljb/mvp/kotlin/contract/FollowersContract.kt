package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Follower
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface FollowersContract {

    interface IView : ListContract.IView<Follower>

    interface IPresenter : ListContract.IPresenter

    interface IModel : IModelContract {
        fun getFollowers(page: Int): Observable<MutableList<Follower>>
    }
}