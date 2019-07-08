package com.ljb.mvp.kotlin.view.fragment

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.view.act.WebViewActivity
import com.ljb.mvp.kotlin.adapter.rv.FollowersAdapter
import com.ljb.mvp.kotlin.adapter.rv.FollowersDecoration
import com.ljb.mvp.kotlin.contract.FollowersContract
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.presenter.FollowersPresenter
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class FollowersFragment : BaseMvpFragment<FollowersContract.IPresenter>(), FollowersContract.IView,
        LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter by lazy { FollowersAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_followers

    override fun registerPresenter() = FollowersPresenter::class.java

    override fun initView() {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            val manager = GridLayoutManager(context, 3)
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val itemViewType = mAdapter.getItemViewType(position)
                    return if (itemViewType == LoadMoreRecyclerAdapter.TYPE_LOAD_MORE) 3 else 1
                }
            }
            layoutManager = manager
            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@FollowersFragment)
            mAdapter.setOnItemClickListener(this@FollowersFragment)
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

    override fun showPage(data: MutableList<Follower>, page: Int) {
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