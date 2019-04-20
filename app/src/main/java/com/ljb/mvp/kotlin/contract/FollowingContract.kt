package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Following

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface FollowingContract {

    interface IView : ListContract.IView<Following>

    interface IPresenter : ListContract.IPresenter
}