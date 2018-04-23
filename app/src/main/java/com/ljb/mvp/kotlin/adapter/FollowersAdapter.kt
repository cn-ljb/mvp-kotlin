package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.widget.findViewByIdEx
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.img.ImageLoader
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter

/**
 * Created by L on 2017/7/19.
 */
class FollowersAdapter(mContext: Context, mData: MutableList<Follower>) : LoadMoreRecyclerAdapter<Follower>(mContext, mData) {
    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            FollowersViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_followers, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowersViewHolder) {
            val item = mData[position]
            ImageLoader.load(context = mContext,
                    url = item.avatar_url,
                    img = holder.iv_avatar,
                    loadingImgResId = R.drawable.default_header,
                    loadErrorImgResId = R.drawable.default_header,
                    form = ImageLoader.ImageForm.ROUND)
            holder.tv_follower_name.text = item.login
        }
    }


    class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_avatar by lazy { itemView.findViewByIdEx<ImageView>(R.id.iv_avatar) }
        val tv_follower_name by lazy { itemView.findViewByIdEx<TextView>(R.id.tv_follower_name) }
    }

}