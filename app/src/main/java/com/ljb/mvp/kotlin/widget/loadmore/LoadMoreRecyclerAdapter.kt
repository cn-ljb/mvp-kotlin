package com.ljb.mvp.kotlin.widget.loadmore

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.ljb.kt.utils.NetLog

/**
 * Created by L on 2017/7/24.
 */
abstract class LoadMoreRecyclerAdapter<T>(val mContext: Context, var mData: MutableList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val PAGE_DATA_SIZE = 30
        private val TYPE_LOAD_MORE = 0
        private val TYPE_ITEM = 1
    }

    private var mLoadMoreHolder: LoadMoreHolder? = null;

    private var isLoading = false
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mLoadMoreListener: LoadMoreListener? = null

    override fun getItemId(position: Int) = position.toLong()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_LOAD_MORE) {  //加载更多
            if (mLoadMoreHolder == null) {
                val loadView = LayoutInflater.from(mContext).inflate(R.layout.layout_load_more, parent, false)
                mLoadMoreHolder = LoadMoreHolder(loadView, this)
            }
            initLoadStatusForSize(mData)
            return mLoadMoreHolder!!
        }
        return getItemHolder(parent, viewType)
    }

    abstract fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == TYPE_ITEM) {
            onBindData(holder, position)
            //设置点击事件
            mOnItemClickListener?.apply {
                holder.itemView.setOnClickListener {
                    this.onItemClick(holder.itemView, position)
                }
            }

        } else if (itemViewType == TYPE_LOAD_MORE) {
            NetLog.i("TYPE_LOAD_MORE")
            loadMore()
        }
    }

    abstract fun onBindData(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemCount(): Int = mData.size + 1

    override fun getItemViewType(position: Int): Int {
        if (itemCount - 1 == position) {
            return TYPE_LOAD_MORE
        }
        return TYPE_ITEM
    }


    fun onError() {
        isLoading = false
        mLoadMoreHolder?.setStatus(LoadMoreHolder.LoadMoreType.error)
    }


    fun initLoadStatusForSize(data: List<T>) {
        if (data.size < PAGE_DATA_SIZE) {
            setLoadMoreStatus(LoadMoreHolder.LoadMoreType.notMore)
        } else {
            setLoadMoreStatus(LoadMoreHolder.LoadMoreType.loading)
        }
    }

    fun setLoadMoreStatus(status: LoadMoreHolder.LoadMoreType) {
        isLoading = false
        mLoadMoreHolder?.setStatus(status)
    }

    fun onNotMore() {
        setLoadMoreStatus(LoadMoreHolder.LoadMoreType.notMore)
    }


    /**
     *  1、创建加载更多View时触发
     *  2、点击重新加载时触发
     * */
    fun loadMore() {
        if (!isLoading && mLoadMoreListener != null && mLoadMoreHolder!!.getType() == LoadMoreHolder.LoadMoreType.loading) {
            isLoading = true
            mLoadMoreListener!!.onLoadMore()
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }

    fun setOnLoadMoreListener(listener: LoadMoreListener) {
        mLoadMoreListener = listener
    }


    interface LoadMoreListener {
        fun onLoadMore()
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


}