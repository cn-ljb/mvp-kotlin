package com.ljb.mvp.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.FollowersAdapter
import com.ljb.mvp.kotlin.contract.FollowersContract
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.presenter.FollowersPresenter
import com.wuba.weizhang.mvp.BaseMvpFragment
import com.yimu.store.widget.PageStateLayout
import com.yimu.store.widget.loadmore.LoadMoreRecyclerAdapter
import kotlinx.android.synthetic.main.layout_recycler_view.*

/**
 * Created by L on 2017/7/19.
 */
class FollowersFragment : BaseMvpFragment<FollowersPresenter>(), FollowersContract.IFollowersView, PageStateLayout.PageStateCallBack, LoadMoreRecyclerAdapter.LoadMoreListener {


    private lateinit var mPageLayout: PageStateLayout
    private var mAdapter: FollowersAdapter? = null


    override fun createPresenter() = FollowersPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_followers, null)
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
        val manager = GridLayoutManager(context, 3)
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

    override fun showPage(data: MutableList<Follower>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                mPageLayout.setPage(PageStateLayout.STATE_EMPTY)
            } else {
                mPageLayout.setPage(PageStateLayout.STATE_SUCCEED)
                if (mAdapter == null) {
                    mAdapter = FollowersAdapter(activity, data)
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