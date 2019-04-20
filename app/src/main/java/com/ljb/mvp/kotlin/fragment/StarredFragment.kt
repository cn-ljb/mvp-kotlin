package com.ljb.mvp.kotlin.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.act.WebViewActivity
import com.ljb.mvp.kotlin.adapter.rv.StarredAdapter
import com.ljb.mvp.kotlin.contract.StarredContract
import com.ljb.mvp.kotlin.domain.Starred
import com.ljb.mvp.kotlin.presenter.StarredPresenter
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_starred.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class StarredFragment : BaseMvpFragment<StarredContract.IPresenter>(), StarredContract.IView,
        LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter: StarredAdapter by lazy { StarredAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_starred

    override fun registerPresenter() = StarredPresenter::class.java

    override fun initView() {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@StarredFragment)
            mAdapter.setOnItemClickListener(this@StarredFragment)
        }
    }

    override fun initData() {
        getPresenter().onRefresh()
    }

    override fun onLoadMore() {
        getPresenter().onLoadMore()
    }

    private fun onReload() {
        page_layout.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: MutableList<Starred>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                page_layout.setPage(PageState.STATE_EMPTY)
            } else {
                page_layout.setPage(PageState.STATE_SUCCESS)
                mAdapter.mData.clear()
                mAdapter.mData.addAll(data)
                mAdapter.onLoadStatus(data)
            }
        } else {
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatus(data)
        }
    }

    override fun errorPage(t: Throwable, page: Int) {
        if (page == 1) {
            page_layout.setPage(PageState.STATE_ERROR)
        } else {
            mAdapter.onErrorStatus()
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val itemData = mAdapter.mData[position]
        WebViewActivity.startActivity(activity!!, itemData.html_url)
    }
}