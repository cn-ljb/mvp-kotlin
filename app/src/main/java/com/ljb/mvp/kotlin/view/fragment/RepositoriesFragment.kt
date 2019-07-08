package com.ljb.mvp.kotlin.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.view.act.WebViewActivity
import com.ljb.mvp.kotlin.adapter.rv.RepositoriesAdapter
import com.ljb.mvp.kotlin.contract.RepositoriesContract
import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.presenter.RepositoriesPresenter
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.android.synthetic.main.layout_refresh_recycler_view.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Repos page
 * Created by L on 2017/7/18.
 */
class RepositoriesFragment : BaseMvpFragment<RepositoriesContract.IPresenter>(), RepositoriesContract.IView,
        LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter by lazy { RepositoriesAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_repos

    override fun registerPresenter() = RepositoriesPresenter::class.java

    override fun initView() {
        page_layout.setOnPageErrorClickListener { onReload() }
        refresh_layout.apply {
            setColorSchemeResources(R.color.color_39B6DF)
            setOnRefreshListener { getPresenter().onRefresh() }
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@RepositoriesFragment)
            mAdapter.setOnItemClickListener(this@RepositoriesFragment)
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

    override fun showPage(data: MutableList<Repository>, page: Int) {
        if (page == 1) {
            refresh_layout.isRefreshing = false
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