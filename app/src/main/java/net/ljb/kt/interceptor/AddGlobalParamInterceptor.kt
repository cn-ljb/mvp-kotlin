package net.ljb.kt.interceptor

import com.ljb.mvp.kotlin.common.GITHUB_CLIENT_ID
import com.ljb.mvp.kotlin.common.GITHUB_CLIENT_SECRET
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 添加公共参数
 * */
class AddGlobalParamInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val paramMap = mapOf(
                "client_id" to GITHUB_CLIENT_ID,
                "client_secret" to GITHUB_CLIENT_SECRET
        )
        val oldRequest = chain.request()
        // 添加新的参数
        val authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())

        paramMap.map {
            authorizedUrlBuilder.addQueryParameter(it.key, it.value)
        }

        // 新的请求
        val newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build()
        return chain.proceed(newRequest)
    }

}