package com.yimu.store.widget.loadmore

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import com.ljb.mvp.kotlin.R


/**
 * Created by L on 2017/1/16.
 */
class LoadMoreHolder(view: View, private val mAdapter: LoadMoreRecyclerAdapter<*>) : RecyclerView.ViewHolder(view) {

    private var mCurType: LoadMoreType = LoadMoreType.loading
    private var rl_more_loading: RelativeLayout? = null
    private var rl_more_error: RelativeLayout? = null
    private var rl_more_not: RelativeLayout? = null

    enum class LoadMoreType {
        loading, error, noMore
    }

    init {
        initView(view)
        initStatus()
    }

    private fun initView(view: View) {
        rl_more_error = view.findViewById(R.id.rl_more_error) as RelativeLayout
        rl_more_loading = view.findViewById(R.id.rl_more_loading) as RelativeLayout
        rl_more_not = view.findViewById(R.id.rl_more_not) as RelativeLayout
        rl_more_error!!.setOnClickListener { reLoadMore() }
    }

    private fun initStatus() {
        rl_more_loading!!.visibility = if (mCurType == LoadMoreType.loading) View.VISIBLE else View.GONE
        rl_more_error!!.visibility = if (mCurType == LoadMoreType.error) View.VISIBLE else View.GONE
        rl_more_not!!.visibility = if (mCurType == LoadMoreType.noMore) View.VISIBLE else View.GONE
    }

    private fun reLoadMore() {
        if (mCurType == LoadMoreType.error) {
            mCurType = LoadMoreType.loading
            initStatus()
            mAdapter.loadMore()
        }
    }

    fun getType(): LoadMoreType = mCurType

    fun setStatus(mCurType: LoadMoreType) {
        this.mCurType = mCurType
        initStatus()
    }
}
