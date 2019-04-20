package com.ljb.mvp.kotlin.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.act.WebViewActivity
import com.ljb.mvp.kotlin.adapter.rv.EventAdapter
import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.presenter.EventPresenter
import com.ljb.mvp.kotlin.widget.dialog.LoadingDialog
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class EventsFragment : BaseMvpFragment<EventsContract.IPresenter>(), EventsContract.IView,
        LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter by lazy { EventAdapter(activity!!, mutableListOf()) }
    private val mLoadingDialog by lazy { LoadingDialog(activity!!) }

    override fun getLayoutId() = R.layout.fragment_events

    override fun registerPresenter() = EventPresenter::class.java

    override fun initView() {
        page_layout.setOnPageErrorClickListener { onReload() }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@EventsFragment)
            mAdapter.setOnItemClickListener(this@EventsFragment)
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

    override fun showPage(data: MutableList<Event>, page: Int) {
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
        mLoadingDialog.show()
        getPresenter().getReposFromUrl(itemData.repo.url)
    }

    override fun setRepos(repos: Repository?) {
        mLoadingDialog.dismiss()
        repos?.let {
            WebViewActivity.startActivity(activity!!, it.html_url)
        }
    }


}