package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.widget.findViewByIdEx
import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.domain.EventCommit
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import java.lang.StringBuilder

/**
 * Created by L on 2017/7/19.
 */
class EventAdapter(mContext: Context, mData: MutableList<Event>) : LoadMoreRecyclerAdapter<Event>(mContext, mData) {

    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            EventViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EventViewHolder) {
            holder.tv_type.text = mData[position].type
            holder.tv_project_name.text = mData[position].repo.name
            holder.tv_url.text = mData[position].repo.url
            if (mData[position].payload.commits == null) {
                holder.tv_commit.visibility = View.VISIBLE
                if (mData[position].payload.action != null) {
                    holder.tv_commit.text = mContext.getString(R.string.action, mData[position].payload.action)
                } else {
                    holder.tv_commit.visibility = View.GONE
                }
                holder.tv_submitter.text = mData[position].actor.display_login
            } else {
                holder.tv_commit.visibility = View.VISIBLE
                holder.tv_commit.text = getCommitStr(mData[position].payload.commits!!)
                holder.tv_submitter.text = mData[position].payload.commits!![0].author.name
            }
            holder.tv_time.text = mData[position].created_at
        }
    }

    private fun getCommitStr(commits: List<EventCommit>): String {
        return StringBuilder("commits:\n").apply {
            for ((index, comment) in commits.withIndex()) {
                if (index <= 3) {
                    if (index != 0) {
                        append("--- --- ---\n")
                    }
                    append("${comment.message}\n")
                } else {
                    append("...")
                    break
                }
            }
        }.toString()
    }


    override fun getItemCount(): Int = mData.size

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_type by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_type) }
        val tv_project_name by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_project_name) }
        val tv_url by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_url) }
        val tv_commit by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_commit) }
        val tv_submitter by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_submitter) }
        val tv_time by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_time) }
    }

}