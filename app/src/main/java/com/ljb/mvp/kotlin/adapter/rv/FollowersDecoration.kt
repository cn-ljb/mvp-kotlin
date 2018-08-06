package com.ljb.mvp.kotlin.adapter.rv

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ljb.mvp.kotlin.utils.UIUtils

class FollowersDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = UIUtils.dip2px(view.context, 2.5f)
        outRect.bottom = UIUtils.dip2px(view.context, 2.5f)
        outRect.left = UIUtils.dip2px(view.context, 5f)
        outRect.right = UIUtils.dip2px(view.context, 5f)
    }
}