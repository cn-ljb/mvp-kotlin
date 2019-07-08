package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.WebViewContract
import com.ljb.mvp.kotlin.model.WebViewModel
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class WebViewPresenter : BaseMvpPresenter<WebViewContract.IView, WebViewContract.IModel>(), WebViewContract.IPresenter {

    override fun registerModel() = WebViewModel::class.java

}
