package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Event

/**
 * Created by L on 2017/9/14.
 */
interface EventsContract {

    interface IView : ListContract.IView<Event>

    interface IPresenter : ListContract.IPresenter
}