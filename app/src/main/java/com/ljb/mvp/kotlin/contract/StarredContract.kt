package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Starred

/**
 * Created by L on 2017/9/21.
 */
interface StarredContract {

    interface IView : ListContract.IView<Starred>

    interface IPresenter : ListContract.IPresenter
}