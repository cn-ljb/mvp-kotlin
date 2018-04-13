package com.ljb.mvp.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.EventAdapter
import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.presenter.EventPresenter
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.mvp.kotlin.mvp.BaseMvpFragment
import com.ljb.mvp.kotlin.widget.PageStateLayout
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.layout_recycler_view.*

/**
 * Created by L on 2017/7/19.
 */
class EventsFragment : BaseMvpFragment<EventPresenter>(),
        EventsContract.IEventsView,
        PageStateLayout.PageStateCallBack,
        LoadMoreRecyclerAdapter.LoadMoreListener {

    private val mAdapter by lazy { EventAdapter(activity, mutableListOf()) }

    override fun createPresenter() = EventPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_events, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    private fun initView() {
        page_layout.apply {
            setContentView(View.inflate(activity, R.layout.layout_recycler_view, null))
            addCallBack(this@EventsFragment)
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@EventsFragment)
        }
    }

    private fun initData() {
        mPresenter.onRefresh()
    }

    override fun onLoadMore() {
        mPresenter.onLoadMore()
    }

    override fun onErrorClick() {
        page_layout.setPage(PageStateLayout.STATE_LOADING)
        mPresenter.onRefresh()
    }

    override fun showPage(data: MutableList<Event>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                page_layout.setPage(PageStateLayout.STATE_EMPTY)
            } else {
                page_layout.setPage(PageStateLayout.STATE_SUCCEED)
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
            page_layout.setPage(PageStateLayout.STATE_ERROR)
        } else {
            mAdapter.onError()
        }
    }

}