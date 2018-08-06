package com.ljb.mvp.kotlin.img

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Looper
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.ljb.mvp.kotlin.R
import jp.wasabeef.glide.transformations.CropCircleTransformation
import jp.wasabeef.glide.transformations.CropTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

object ImageLoader {

    private val mDefRequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.color.color999)
            .error(R.color.color999)

    private val mCircleRequestOptions = RequestOptions
            .bitmapTransform(CropCircleTransformation())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.color.color999)
            .error(R.color.color999)


    fun getRoundRequest(radius: Int, type: RoundedCornersTransformation.CornerType): RequestOptions {
        return RequestOptions
                .bitmapTransform(RoundedCornersTransformation(radius, 0, type))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.colorD2D2D2)
                .error(R.color.colorD2D2D2)
    }

    fun getCircleRequest(): RequestOptions {
        return mCircleRequestOptions
    }


    fun load(context: Context, url: String?, img: ImageView,
             request: RequestOptions = mDefRequestOptions) {
        if (url == null) return
        checkContext(context)
        val imgUrl = nvlUrl(url)
        Glide.with(context).load(imgUrl).apply(request).into(img)
    }


    fun load(context: Context, resId: Int?, img: ImageView,
             request: RequestOptions = mDefRequestOptions) {
        if (resId == null || resId == 0) return
        checkContext(context)
        Glide.with(context).load(resId).apply(request).into(img)
    }

    private fun checkContext(context: Context) {
        if (Thread.currentThread() != Looper.getMainLooper().thread) return
        if (context is Activity && (context.isFinishing || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && context.isDestroyed))) {
            return
        }
    }

    private fun nvlUrl(url: String): String {
        return if (!(url.startsWith("http", true) || url.startsWith("https", true))) {
            "http:$url"
        } else {
            url
        }
    }

    fun downImage(context: Context, url: String?, callBack: (bitmap: Bitmap?) -> Unit) {
        if (TextUtils.isEmpty(url)) {
            callBack.invoke(null)
            return
        }
        Glide.with(context).asBitmap().load(nvlUrl(url!!)).into(object : SimpleTarget<Bitmap>() {

            override fun onLoadFailed(errorDrawable: Drawable?) = callBack.invoke(null)

            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) = callBack.invoke(bitmap)

        })
    }


}