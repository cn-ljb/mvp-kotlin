package com.ljb.mvp.kotlin.img

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ljb.mvp.kotlin.R

object ImageLoader {

    enum class ImageForm {
        STANDARD, CIRCLE, ROUND
    }

    fun load(context: Context, url: String, img: ImageView,
             loadingImgResId: Int = R.color.color999,
             loadErrorImgResId: Int = R.color.color333,
             form: ImageForm = ImageForm.STANDARD) {
        val imageBuilder = Glide.with(context).load(url)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(loadingImgResId)
                .error(loadErrorImgResId)
        when (form) {
            ImageForm.CIRCLE -> imageBuilder.transform(GlideCircleTransform(context))
            ImageForm.ROUND -> imageBuilder.transform(GlideRoundTransform(context))
            ImageForm.STANDARD -> Unit
        }
        imageBuilder.into(img)
    }

}