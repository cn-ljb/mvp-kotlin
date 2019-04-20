package com.ljb.mvp.kotlin.webview

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView

/**
 * Author:Ljb
 * Time:2019/4/3
 * There is a lot of misery in life
 **/
interface WebActionCallBack {

    /**
     * 页面Title回调
     * */
    fun onReceivedTitle(view: WebView?, title: String?)

    /**
     * 页面发生错误
     * */
    fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?)

    /**
     * 是否拦截Url
     * */
    fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean

    /**
     * 页面加载完毕
     * */
    fun onPageFinished(view: WebView?, url: String?)

    /**
     * 页面开始加载
     * */
    fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)

    /**
     * 页面加载进度
     * */
    fun onProgressChanged(view: WebView?, newProgress: Int)

}