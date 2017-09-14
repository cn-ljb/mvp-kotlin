package com.ljb.mvp.kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ljb.mvp.kotlin.R


/**
 * 底部Tab导航栏
 * Created by L on 2017/7/10.
 */
class TabGroupView : LinearLayout {

    private var mOnItemClickListener: ((position: Int) -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        mOnItemClickListener = listener
    }

    fun setAdapter(adapter: TabAdapter?) {
        if (adapter != null && adapter.getCount() > 0) {
            for (i in 0 until adapter.getCount()) {
                val tabView = adapter.getTabView(i)
                val params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                params.weight = 1f
                params.gravity = Gravity.CENTER
                addView(tabView, params)
                tabView.setOnClickListener { mOnItemClickListener?.invoke(i) }
            }
        }
    }

    fun setSelectedPosition(position: Int) {
        initUnSelected()
        getChildAt(position).findViewById(R.id.bottom_tab_icon).let { it.isSelected = true }
        getChildAt(position).findViewById(R.id.bottom_tab_text).let { it.isSelected = true }
    }

    private fun initUnSelected() {
        (0..childCount - 1).mapNotNull {
            getChildAt(it).findViewById(R.id.bottom_tab_icon).let { it.isSelected = false }
            getChildAt(it).findViewById(R.id.bottom_tab_text).let { it.isSelected = false }
        }
    }


    interface TabAdapter {
        fun getCount(): Int
        fun getTabView(position: Int): View
    }


}
