package com.ljb.mvp.kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.ljb.mvp.kotlin.R


/**
 * 页面切换管理的Layout
 * Created by Ljb on 2016/1/8.
 */
class PageStateLayout : FrameLayout {

    enum class PageState {
        /**
         * 正在加载状态
         */
        STATE_LOADING,
        /**
         * 错误状态
         */
        STATE_ERROR,
        /**
         * 空数据状态
         */
        STATE_EMPTY,
        /**
         * 成功状态
         */
        STATE_SUCCEED
    }


    private var currentPageState = PageState.STATE_LOADING
    private var mPageErrorClickListener: (() -> Unit)? = null
    private var mPageCloseClickListener: (() -> Unit)? = null
    private var mLoadingView: View? = null
    private var mErrorView: View? = null
    private var mEmptyView: View? = null
    private var mSucceedView: View? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        mLoadingView = initLoadingView(context)
        mEmptyView = initEmptyView(context)
        mErrorView = initErrorView(context)

        if (mLoadingView != null) {
            addView(mLoadingView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        if (mEmptyView != null) {
            addView(mEmptyView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        if (mErrorView != null) {
            addView(mErrorView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        updatePage()
    }


    /**
     * 初始化错误界面
     */
    private fun initErrorView(context: Context) = LayoutInflater.from(context).inflate(R.layout.loading_page_error, this, false).apply {
        findViewById<View>(R.id.tv_reload).setOnClickListener { onPageErrorClick() }
    }

    /**
     * 初始化空界面
     */
    private fun initEmptyView(context: Context) = LayoutInflater.from(context).inflate(R.layout.loading_page_empty, this, false)
    /**
     * 初始化加载中界面
     */
    private fun initLoadingView(context: Context) = LayoutInflater.from(context).inflate(R.layout.loading_page_load, this, false)

    /**
     * 根据状态显示界面
     */
    private fun updatePage() {
        if (null != mSucceedView) {
            mSucceedView!!.visibility = if (currentPageState == PageState.STATE_SUCCEED) View.VISIBLE else View.INVISIBLE
        }
        if (null != mErrorView) {
            mErrorView!!.visibility = if (currentPageState == PageState.STATE_ERROR) View.VISIBLE else View.GONE
        }
        if (null != mEmptyView) {
            mEmptyView!!.visibility = if (currentPageState == PageState.STATE_EMPTY) View.VISIBLE else View.GONE
        }
        if (null != mLoadingView) {
            mLoadingView!!.visibility = if (currentPageState == PageState.STATE_LOADING) View.VISIBLE else View.GONE
        }
    }

    fun setPage(pageState: PageState) {
        this.currentPageState = pageState
        updatePage()
    }

    fun getPageState() = currentPageState

    fun setContentView(resId: Int) {
        setContentView(LayoutInflater.from(context).inflate(resId, this, false))
    }

    fun setContentView(view: View) {
        if (mSucceedView != null) {
            removeView(mSucceedView)
        }
        mSucceedView = view
        mSucceedView!!.visibility = View.GONE
        addView(mSucceedView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
    }

    fun setErrorView(resId: Int) {
        setErrorView(LayoutInflater.from(context).inflate(resId, this, false))
    }

    fun setErrorView(view: View) {
        if (mErrorView != null) {
            removeView(mErrorView)
        }
        mErrorView = view
        mErrorView?.visibility = View.GONE
        addView(mErrorView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
    }

    fun setLoadView(resId: Int) {
        setLoadView(LayoutInflater.from(context).inflate(resId, this, false))
    }

    fun setLoadView(view: View) {
        if (mLoadingView != null) {
            removeView(mLoadingView)
        }
        mLoadingView = view
        mLoadingView?.visibility = View.GONE
        addView(mLoadingView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
    }

    fun setEmptyView(resId: Int) {
        setEmptyView(LayoutInflater.from(context).inflate(resId, this, false))
    }

    fun setEmptyView(view: View) {
        if (mEmptyView != null) {
            removeView(mEmptyView)
        }
        mEmptyView = view
        mEmptyView?.visibility = View.GONE
        addView(mEmptyView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
    }

    /**
     * 设置页面出错后的点击事件
     * */
    fun setOnPageErrorClickListener(listener: () -> Unit) {
        this.mPageErrorClickListener = listener
    }

    private fun onPageErrorClick() {
        mPageErrorClickListener?.invoke()
    }

}
