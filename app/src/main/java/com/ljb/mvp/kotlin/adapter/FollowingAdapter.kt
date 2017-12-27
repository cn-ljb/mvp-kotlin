package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.domain.Following
import com.ljb.mvp.kotlin.img.GlideRoundTransform
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter

/**
 * Created by L on 2017/10/9.
 */
class FollowingAdapter(mContext: Context, mData: MutableList<Following>) : LoadMoreRecyclerAdapter<Following>(mContext, mData) {

    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            FollowingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_following, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowingViewHolder) {
            val item = mData[position]
            holder.tv_following_name.text = item.login
            Glide.with(mContext).load(item.avatar_url)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_header)
                    .error(R.drawable.default_header)
                    .transform(GlideRoundTransform(mContext))
                    .into(holder.iv_avatar)
        }
    }


    class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_following_name by lazy { itemView.findViewById(R.id.tv_following_name) as TextView }
        val iv_avatar by lazy { itemView.findViewById(R.id.iv_avatar) as ImageView }
//        val tv_company by lazy { itemView.findViewById(R.id.tv_company) as TextView }
//        val tv_location by lazy { itemView.findViewById(R.id.tv_location) as TextView }
    }
}