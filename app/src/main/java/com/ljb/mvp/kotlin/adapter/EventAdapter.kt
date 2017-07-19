package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R

/**
 * Created by L on 2017/7/19.
 */
class EventAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(View.inflate(mContext, R.layout.item_event, null))
    }

    override fun getItemCount(): Int {
        return 10
    }

    class EventViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}