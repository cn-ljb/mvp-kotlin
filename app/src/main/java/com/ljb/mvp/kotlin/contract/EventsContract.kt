package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.domain.Repository

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface EventsContract {

    interface IView : ListContract.IView<Event> {
        fun setRepos(repos: Repository?)
    }

    interface IPresenter : ListContract.IPresenter {
        fun getReposFromUrl(url: String)
    }
}