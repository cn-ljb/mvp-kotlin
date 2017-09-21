package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.domain.Starred
import com.yimu.store.widget.loadmore.LoadMoreRecyclerAdapter

/**
 * Created by L on 2017/7/19.
 */
class StarredAdapter(mContext: Context, mData: MutableList<Starred>) : LoadMoreRecyclerAdapter<Starred>(mContext, mData) {

    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            StarredViewHolder(View.inflate(mContext, R.layout.item_starred, null))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StarredViewHolder) {
            val item = mData[position]
            holder.tv_project_name.text = item.name
            holder.tv_language.text = item.language
            holder.tv_author.text = item.owner.login
            holder.tv_url.text = item.html_url
            holder.tv_star.text = "star:${item.stargazers_count}"
            holder.tv_fork.text = "star:${item.forks}"
            holder.tv_issues.text = "star:${item.open_issues_count}"
            holder.tv_update_time.text = "update: ${item.updated_at}"
            holder.tv_create_time.text = "create: ${item.created_at}"
        }
    }


    class StarredViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_project_name by lazy { itemView.findViewById(R.id.tv_project_name) as TextView }
        val tv_language by lazy { itemView.findViewById(R.id.tv_language) as TextView }
        val tv_author by lazy { itemView.findViewById(R.id.tv_author) as TextView }
        val tv_url by lazy { itemView.findViewById(R.id.tv_url) as TextView }
        val tv_star by lazy { itemView.findViewById(R.id.tv_star) as TextView }
        val tv_fork by lazy { itemView.findViewById(R.id.tv_fork) as TextView }
        val tv_issues by lazy { itemView.findViewById(R.id.tv_issues) as TextView }
        val tv_update_time by lazy { itemView.findViewById(R.id.tv_update_time) as TextView }
        val tv_create_time by lazy { itemView.findViewById(R.id.tv_create_time) as TextView }
    }

}