package com.wuba.weizhang.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.domain.TabBean
import com.ljb.mvp.kotlin.widget.TabGroupView

/**
 * Created by L on 2017/7/12.
 */
class MainTabAdapter(val mContext: Context, val mData: List<TabBean>) : TabGroupView.TabAdapter {

    override fun getCount() = mData.size

    override fun getTabView(position: Int): View {
        val itemBean = mData[position]
        val view = View.inflate(mContext, R.layout.bottom_tab_defalut, null)
        (view.findViewById(R.id.bottom_tab_icon) as ImageView).setImageResource(itemBean.iconResID)
        (view.findViewById(R.id.bottom_tab_text) as TextView).setText(itemBean.textResID)
        return view
    }
}