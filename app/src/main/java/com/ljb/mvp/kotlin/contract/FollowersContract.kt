package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Follower

/**
 * Created by L on 2017/9/22.
 */
interface FollowersContract {

    interface IView :  ListContract.IView<Follower>

    interface IPresenter : ListContract.IPresenter
}