package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.WebViewContract
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class WebViewPresenter : BaseMvpPresenter<WebViewContract.IView>(), WebViewContract.IPresenter
