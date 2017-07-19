package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R

/**
 * Created by L on 2017/7/19.
 */
class StarredAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return StarredViewHolder(View.inflate(mContext, R.layout.item_starred, null))
    }

    override fun getItemCount(): Int {
        return 10
    }

    class StarredViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}