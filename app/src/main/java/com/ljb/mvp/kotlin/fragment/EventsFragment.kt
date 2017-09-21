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
import com.wuba.weizhang.mvp.BaseMvpFragment
import com.yimu.store.widget.PageStateLayout
import com.yimu.store.widget.loadmore.LoadMoreRecyclerAdapter
import kotlinx.android.synthetic.main.layout_recycler_view.*

/**
 * Created by L on 2017/7/19.
 */
class EventsFragment : BaseMvpFragment<EventPresenter>(), EventsContract.IEventsView, PageStateLayout.PageStateCallBack, LoadMoreRecyclerAdapter.LoadMoreListener {

    private lateinit var mPageLayout: PageStateLayout
    private var mAdapter: EventAdapter? = null

    override fun createPresenter() = EventPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_events, null)
        mPageLayout = view.findViewById(R.id.page_layout) as PageStateLayout
        mPageLayout.setContentView(View.inflate(activity, R.layout.layout_recycler_view, null))
        mPageLayout.addCallBack(this)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }


    private fun initView() {
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = manager
    }

    private fun initData() {
        mPresenter.onRefresh()
    }

    override fun onLoadMore() {
        mPresenter.onLoadMore()
    }

    override fun onErrorClick() {
        mPageLayout.setPage(PageStateLayout.STATE_LOADING)
        mPresenter.onRefresh()
    }

    override fun showPage(data: MutableList<Event>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                mPageLayout.setPage(PageStateLayout.STATE_EMPTY)
            } else {
                mPageLayout.setPage(PageStateLayout.STATE_SUCCEED)
                if (mAdapter == null) {
                    mAdapter = EventAdapter(activity, data)
                    recyclerview.adapter = mAdapter
                    mAdapter!!.setOnLoadMoreListener(this)
                } else {
                    mAdapter!!.mData.clear()
                    mAdapter!!.mData.addAll(data)
                    mAdapter!!.initLoadStatusForSize(data)
                    mAdapter!!.notifyDataSetChanged()
                }
            }
        } else {
            if (data.isEmpty()) {
                mAdapter!!.onNotMore()
            } else {
                mAdapter!!.mData.addAll(data)
                mAdapter!!.initLoadStatusForSize(data)
                mAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun errorPage(t: Throwable, page: Int) {
        if (page == 1) {
            mPageLayout.setPage(PageStateLayout.STATE_ERROR)
        } else {
            mAdapter!!.onError()
        }
    }

}