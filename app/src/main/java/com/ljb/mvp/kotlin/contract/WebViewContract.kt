package com.ljb.mvp.kotlin.contract

import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface WebViewContract {

    interface IView : IViewContract

    interface IPresenter : IPresenterContract
}
