package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.domain.Event
import com.wuba.weizhang.mvp.IBasePresenter
import com.wuba.weizhang.mvp.IBaseView

/**
 * Created by L on 2017/9/14.
 */
interface EventsContract {

    interface IEventsView : IBaseView {
        fun showEvents(it: List<Event>)
    }

    interface IEventsPresenter : IBasePresenter<IEventsView> {

    }
}