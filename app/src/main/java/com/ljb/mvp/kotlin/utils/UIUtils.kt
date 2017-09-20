package com.ljb.mvp.kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.ljb.mvp.kotlin.KotlinApplication


object UIUtils {


    /**
     * 全局上下文环境

     * @return
     */
    val context: Context
        get() = KotlinApplication.mContext

    /**
     * 获取屏幕宽度
     */
    val screenWidth: Int
        @SuppressLint("NewApi")
        get() {
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

    /**
     * 获取屏幕高度
     */
    val screenHeight: Int
        @SuppressLint("NewApi")
        get() {
            val wm = context.getSystemService(
                    Context.WINDOW_SERVICE) as WindowManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                val size = Point()
                wm.defaultDisplay.getSize(size)
                return size.y
            } else {
                val d = wm.defaultDisplay
                return d.height
            }
        }

    /**
     * dp转px

     * @param dip
     * *
     * @return
     */
    fun dip2px(dip: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dip * scale + 0.5f).toInt()
    }

    /**
     * px转换dip
     */

    fun px2dip(px: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun px2sp(pxValue: Float): Int {
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
    fun sp2px(spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * getResources

     * @return Resources
     */
    val resources: Resources
        get() = context.resources

    /**
     * 通过资源id获取对应String数组
     * @param id
     * *
     * @return
     */
    fun getStringArray(id: Int): Array<String> {
        return resources.getStringArray(id)
    }

    /**
     * 通过资源id获取对应Int数组

     * @param id
     * *
     * @return
     */
    fun getIntegerArray(id: Int): IntArray {
        return resources.getIntArray(id)
    }

    /**
     * 通过资源id获取对应String

     * @param id
     * *
     * @return
     */
    fun getString(id: Int): String {
        return resources.getString(id)
    }

    /**
     * 通过资源id获取对应颜色

     * @param id
     * *
     * @return
     */
    fun getColor(id: Int): Int = resources.getColor(id)

    fun getDrawable(id: Int): Drawable {
        return resources.getDrawable(id)
    }

    /**
     * 获取View的缩略图

     * @param view
     * *
     * @return ImageView
     */
    fun getDrawingCacheView(view: View): ImageView {
        view.destroyDrawingCache()
        view.isDrawingCacheEnabled = true
        val cache = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        val iv = ImageView(context)
        iv.setImageBitmap(cache)
        return iv
    }

    fun measureViewHeight(view: View): Int {
        val measuredWidth = view.measuredWidth
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY) //宽度不变，精确值
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST) //自动计算，最高上限
        view.measure(widthMeasureSpec, heightMeasureSpec)
        return view.measuredHeight
    }

}
