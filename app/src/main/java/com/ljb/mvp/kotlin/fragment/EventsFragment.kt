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
import com.ljb.mvp.kotlin.presenter.EventsPresenter
import com.wuba.weizhang.mvp.BaseMvpFragment
import com.yimu.store.widget.PageStateLayout
import kotlinx.android.synthetic.main.layout_recycler_view.*

/**
 * Created by L on 2017/7/19.
 */
class EventsFragment : BaseMvpFragment<EventsPresenter>(), EventsContract.IEventsView, PageStateLayout.PageStateCallBack {

    private var mView: View? = null
    private lateinit var mPageLayout: PageStateLayout
    private val mAdapter by lazy { EventAdapter(activity) }

    override fun createPresenter() = EventsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_events, null)
            val contentView = View.inflate(activity, R.layout.layout_recycler_view, null)
            mPageLayout = mView!!.findViewById(R.id.page_layout) as PageStateLayout
            mPageLayout.setContentView(contentView)
            mPageLayout.addCallBack(this)
        } else {
            val parent = mView!!.parent
            (parent as? ViewGroup)?.removeView(mView)
        }
        return mView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }


    private fun initView() {
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = manager
        recyclerview.adapter = mAdapter
    }

    private fun initData() {
        mPresenter.startTask()
    }

    override fun onErrorClick() {
        mPageLayout.setPage(PageStateLayout.STATE_LOADING)
        initData()
    }

    override fun showEvents(it: List<Event>) {
        mAdapter.mData.clear()
        mAdapter.mData.addAll(it)
        mAdapter.notifyDataSetChanged()
        mPageLayout.setPage(PageStateLayout.STATE_SUCCEED)
    }

}