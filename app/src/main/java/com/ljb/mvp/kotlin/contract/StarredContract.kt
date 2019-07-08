package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Starred
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface StarredContract {

    interface IView : ListContract.IView<Starred>

    interface IPresenter : ListContract.IPresenter

    interface IModel : IModelContract {
        fun getStarred(page: Int): Observable<MutableList<Starred>>
    }
}