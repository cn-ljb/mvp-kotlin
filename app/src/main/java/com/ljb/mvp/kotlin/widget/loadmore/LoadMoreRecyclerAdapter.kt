package com.ljb.mvp.kotlin.widget.loadmore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ljb.mvp.kotlin.R

/**
 * Created by L on 2017/7/24.
 */
abstract class LoadMoreRecyclerAdapter<T>(val mContext: Context, var mData: MutableList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val PAGE_DATA_SIZE = 30
        const val TYPE_LOAD_MORE = 0
        const val TYPE_ITEM = 1
    }

    private var mLoadMoreHolder: LoadMoreHolder? = null;

    private var isLoading = false
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mLoadMoreListener: LoadMoreListener? = null
    protected var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getItemId(position: Int) = position.toLong()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_LOAD_MORE) {  //加载更多
            if (mLoadMoreHolder == null) {
                val loadView = mLayoutInflater.inflate(R.layout.layout_load_more, parent, false)
                mLoadMoreHolder = LoadMoreHolder(loadView, this)
            }
            if (mData.size < PAGE_DATA_SIZE) {
                setLoadMoreStatus(LoadMoreHolder.LoadMoreType.NoMore)
            } else {
                setLoadMoreStatus(LoadMoreHolder.LoadMoreType.LoadMore)
            }
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


    fun onErrorStatus() {
        isLoading = false
        mLoadMoreHolder?.setStatus(LoadMoreHolder.LoadMoreType.Error)
    }


    fun onLoadStatus(data: List<T>) {
        if (data.size < PAGE_DATA_SIZE) {
            setLoadMoreStatus(LoadMoreHolder.LoadMoreType.NoMore)
        } else {
            setLoadMoreStatus(LoadMoreHolder.LoadMoreType.LoadMore)
        }
        notifyDataSetChanged()
    }

    private fun setLoadMoreStatus(status: LoadMoreHolder.LoadMoreType) {
        isLoading = false
        mLoadMoreHolder?.setStatus(status)
    }


    /**
     *  1、创建加载更多View时触发
     *  2、点击重新加载时触发
     * */
    fun loadMore() {
        if (!isLoading && mLoadMoreListener != null && mLoadMoreHolder!!.getType() == LoadMoreHolder.LoadMoreType.LoadMore) {
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