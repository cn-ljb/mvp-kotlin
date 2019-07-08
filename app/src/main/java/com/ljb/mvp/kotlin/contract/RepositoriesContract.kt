package com.ljb.mvp.kotlin.contract

import com.ljb.mvp.kotlin.contract.base.ListContract
import com.ljb.mvp.kotlin.domain.Repository
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface RepositoriesContract {

    interface IView : ListContract.IView<Repository>

    interface IPresenter : ListContract.IPresenter

    interface IModel : IModelContract {
        fun getRepositories(page: Int): Observable<MutableList<Repository>>
    }
}