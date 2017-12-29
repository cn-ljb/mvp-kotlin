package com.ljb.mvp.kotlin.fragment.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.RepositoriesAdapter
import com.ljb.mvp.kotlin.contract.RepositoriesContract
import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.presenter.RepositoriesPresenter
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.wuba.weizhang.mvp.BaseMvpFragment
import com.yimu.store.widget.PageStateLayout
import kotlinx.android.synthetic.main.layout_refresh_recycler_view.*

/**
 * Created by L on 2017/7/18.
 */
class RepositoriesFragment : BaseMvpFragment<RepositoriesPresenter>(),
        RepositoriesContract.IRepositoriesView,
        PageStateLayout.PageStateCallBack,
        LoadMoreRecyclerAdapter.LoadMoreListener {

    private lateinit var mPageLayout: PageStateLayout
    private var mAdapter: RepositoriesAdapter? = null

    override fun createPresenter() = RepositoriesPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_repos, null)
        mPageLayout = view.findViewById(R.id.page_layout) as PageStateLayout
        mPageLayout.setContentView(View.inflate(activity, R.layout.layout_refresh_recycler_view, null))
        mPageLayout.addCallBack(this)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        refresh_layout.setColorSchemeResources(R.color.colorBlue)
        refresh_layout.setOnRefreshListener { mPresenter.onRefresh() }

        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = manager
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

    override fun showPage(data: MutableList<Repository>, page: Int) {
        if (page == 1) {
            refresh_layout.isRefreshing = false
            if (data.isEmpty()) {
                mPageLayout.setPage(PageStateLayout.STATE_EMPTY)
            } else {
                mPageLayout.setPage(PageStateLayout.STATE_SUCCEED)
                if (mAdapter == null) {
                    mAdapter = RepositoriesAdapter(activity, data)
                    recycler_view.adapter = mAdapter
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