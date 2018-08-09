package com.ljb.mvp.kotlin.img

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import net.ljb.kt.client.HttpClient
import java.io.InputStream

@GlideModule
class OkHttpAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(HttpClient.getHttpClient()))
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
