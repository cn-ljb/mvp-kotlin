package com.ljb.mvp.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.domain.TabBean
import com.ljb.mvp.kotlin.widget.TabGroupView

/**
 * Created by L on 2017/7/12.
 */
class MainTabAdapter(private val mContext: Context, val mData: List<TabBean>) : TabGroupView.TabAdapter {

    private val mLayoutInflater = LayoutInflater.from(mContext)

    override fun getCount() = mData.size

    override fun getTabView(position: Int, parent: ViewGroup?): View {
        val itemBean = mData[position]
        val view = mLayoutInflater.inflate(R.layout.layout_bottom_tab_defalut, parent, false)
        view.findViewById<ImageView>(R.id.bottom_tab_icon).setImageResource(itemBean.iconResID)
        view.findViewById<TextView>(R.id.bottom_tab_text).setText(itemBean.textResID)
        return view
    }

}