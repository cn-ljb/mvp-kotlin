package com.ljb.mvp.kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView


object UIUtils {

    /** 获取屏幕宽度*/
    @SuppressLint("ObsoleteSdkInt")
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(
                Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val size = Point()
            wm.defaultDisplay.getSize(size)
            size.x
        } else {
            val d = wm.defaultDisplay
            d.width
        }
    }


    /** 获取屏幕高度*/
    @SuppressLint("ObsoleteSdkInt")
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(
                Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val size = Point()
            wm.defaultDisplay.getSize(size)
            size.y
        } else {
            val d = wm.defaultDisplay
            d.height
        }
    }


    /**
     * dp转px

     * @param dip
     * *
     * @return
     */
    fun dip2px(context: Context, dip: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dip * scale + 0.5f).toInt()
    }

    /**
     * px转换dip
     */

    fun px2dip(context: Context, px: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变

     * @param spValue
     * *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * *
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /** 通过资源id获取对应String数组*/
    fun getStringArray(context: Context, id: Int): Array<String> {
        return context.resources.getStringArray(id)
    }

    /** 通过资源id获取对应Int数组*/
    fun getIntegerArray(context: Context, id: Int): IntArray {
        return context.resources.getIntArray(id)
    }

    /**通过资源id获取对应String*/
    fun getString(context: Context, id: Int): String {
        return context.resources.getString(id)
    }

    /**通过资源id获取对应颜色*/
    fun getColor(context: Context, id: Int): Int = context.resources.getColor(id)

    /**通过资源id获取对应Drawable*/
    fun getDrawable(context: Context, id: Int): Drawable {
        return context.resources.getDrawable(id)
    }

    /**
     * 获取View的缩略图
     * @param view
     * *
     * @return ImageView
     */
    fun getDrawingCacheView(context: Context, view: View): ImageView {
        view.destroyDrawingCache()
        view.isDrawingCacheEnabled = true
        val cache = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        val iv = ImageView(context)
        iv.setImageBitmap(cache)
        return iv
    }

    /**测量view当前的实际高度*/
    fun measureViewHeight(view: View): Int {
        val measuredWidth = view.measuredWidth
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY) //宽度不变，精确值
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(getScreenHeight(view.context), View.MeasureSpec.AT_MOST) //自动计算，最高上限
        view.measure(widthMeasureSpec, heightMeasureSpec)
        return view.measuredHeight
    }

    /**测量view当前的实际宽度*/
    fun measureViewWidth(view: View): Int {
        val measuredHeight = view.measuredHeight
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(getScreenWidth(view.context), View.MeasureSpec.AT_MOST)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY)
        view.measure(widthMeasureSpec, heightMeasureSpec)
        return view.measuredHeight
    }

}
