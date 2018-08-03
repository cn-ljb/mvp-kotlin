package com.ljb.mvp.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.StarredAdapter
import com.ljb.mvp.kotlin.common.fragment.BaseMvpFragment
import com.ljb.mvp.kotlin.contract.StarredContract
import com.ljb.mvp.kotlin.domain.Starred
import com.ljb.mvp.kotlin.presenter.StarredPresenter
import com.ljb.mvp.kotlin.widget.PageStateLayout
import com.ljb.mvp.kotlin.widget.PageStateLayout.PageState
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_starred.*
import kotlinx.android.synthetic.main.layout_recycler_view.*

/**
 * Created by L on 2017/7/19.
 */
class StarredFragment : BaseMvpFragment<StarredContract.IPresenter>(), StarredContract.IView,
        PageStateLayout.PageStateCallBack,
        LoadMoreRecyclerAdapter.LoadMoreListener {

    private val mAdapter: StarredAdapter by lazy { StarredAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_starred

    override fun registerPresenter() = StarredPresenter::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    override fun initView() {
        page_layout.apply {
            setContentView(View.inflate(activity, R.layout.layout_recycler_view, null))
            addCallBack(this@StarredFragment)
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@StarredFragment)
        }
    }

    override fun initData() {
        getPresenter().onRefresh()
    }

    override fun onLoadMore() {
        getPresenter().onLoadMore()
    }

    override fun onErrorClick() {
        page_layout.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: MutableList<Starred>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                page_layout.setPage(PageState.STATE_EMPTY)
            } else {
                page_layout.setPage(PageState.STATE_SUCCEED)
                mAdapter.mData.clear()
                mAdapter.mData.addAll(data)
                mAdapter.initLoadStatusForSize(data)
                mAdapter.notifyDataSetChanged()
            }
        } else {
            if (data.isEmpty()) {
                mAdapter.onNotMore()
            } else {
                mAdapter.mData.addAll(data)
                mAdapter.initLoadStatusForSize(data)
                mAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun errorPage(t: Throwable, page: Int) {
        if (page == 1) {
            page_layout.setPage(PageState.STATE_ERROR)
        } else {
            mAdapter.onError()
        }
    }
}