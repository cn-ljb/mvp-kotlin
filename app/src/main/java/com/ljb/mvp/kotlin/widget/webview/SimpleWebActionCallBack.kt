package com.ljb.mvp.kotlin.widget.webview

import android.webkit.WebResourceResponse
import android.webkit.WebView

/**
 * Author:Ljb
 * Time:2018/8/14
 * There is a lot of misery in life
 **/
abstract class SimpleWebActionCallBack : WebActionCallBack {

    override fun jsActionCallBack(json: String): Boolean {
        return false
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        return null
    }

    override fun onPageFinished(view: WebView?, url: String?) {
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
    }
}