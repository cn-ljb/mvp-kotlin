package com.ljb.mvp.kotlin.act

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebView
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.contract.WebViewContract
import com.ljb.mvp.kotlin.presenter.WebViewPresenter
import com.ljb.mvp.kotlin.webview.SimpleWebActionCallBack
import com.ljb.mvp.kotlin.webview.WebViewProxy
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_common_title.*
import kotlinx.android.synthetic.main.layout_web.*
import mvp.ljb.kt.act.BaseMvpActivity

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class WebViewActivity : BaseMvpActivity<WebViewContract.IPresenter>(), WebViewContract.IView {

    companion object {
        private const val KEY_URL = "webUrl"
        private const val KEY_TITLE = "webTitle"

        fun startActivity(context: Context, url: String, title: String = "") {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(KEY_URL, url)
            intent.putExtra(KEY_TITLE, title)
            context.startActivity(intent)
        }
    }

    private var mTitle: String? = null
    private var mUrl: String? = null
    private lateinit var mProxy: WebViewProxy

    override fun registerPresenter() = WebViewPresenter::class.java

    override fun getLayoutId() = R.layout.activity_web

    override fun init(savedInstanceState: Bundle?) {
        mTitle = intent.getStringExtra(KEY_TITLE)
        mUrl = intent.getStringExtra(KEY_URL)
        if (TextUtils.isEmpty(mUrl)) finish()
    }

    override fun initView() {
        iv_back.setOnClickListener { onBackPressed() }
        mProxy = WebViewProxy(this, webview, object : SimpleWebActionCallBack() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                if (TextUtils.isEmpty(mTitle)) tv_title.text = title
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                page_layout.setPage(PageState.STATE_LOADING)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                page_layout.setPage(PageState.STATE_SUCCESS)
            }
        })
    }

    override fun initData() {
        if (!TextUtils.isEmpty(mTitle)) {
            tv_title.text = mTitle
        }
        if (TextUtils.isEmpty(mUrl)) return
        if (!mUrl!!.startsWith("http")) {
            mUrl = "http://$mUrl"
        }
        mProxy.loadUrl(mUrl!!)
    }

    override fun onPause() {
        super.onPause()
        mProxy.onWebViewPause()
    }

    override fun onResume() {
        super.onResume()
        mProxy.onWebViewResume()
    }

    override fun onDestroy() {
        mProxy.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (mProxy.canGoBack()) {
            mProxy.goBack()
        } else {
            super.onBackPressed()
        }
    }

}
