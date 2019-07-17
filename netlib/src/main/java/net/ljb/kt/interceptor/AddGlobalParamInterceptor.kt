package net.ljb.kt.interceptor

import net.ljb.kt.HttpConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author:Ljb
 * Time:2018/8/9
 * There is a lot of misery in life
 **/
class AddGlobalParamInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val paramMap = HttpConfig.getParam()
        val headerMap = HttpConfig.getHeader()
        val oldRequest = chain.request()
        val newRequestBuilder = oldRequest.newBuilder()

        // 添加公共Header
        if (headerMap != null && headerMap.isNotEmpty()) {
            val newHeaderBuilder = oldRequest.headers().newBuilder()
            headerMap.map { newHeaderBuilder.add(it.key, it.value) }
            newRequestBuilder.headers(newHeaderBuilder.build())
        }

        // 添加公共的Param
        if (paramMap != null && paramMap.isNotEmpty()) {
            val newUrlBuilder = oldRequest.url().newBuilder()
            newUrlBuilder.scheme(oldRequest.url().scheme())
            newUrlBuilder.host(oldRequest.url().host())
            paramMap.map { newUrlBuilder.addQueryParameter(it.key, it.value) }
            newRequestBuilder.url(newUrlBuilder.build())
        }

        // 新的请求
        val newRequest = newRequestBuilder
                .method(oldRequest.method(), oldRequest.body())
                .build()
        try {
            return chain.proceed(newRequest)
        } catch (e: Exception) {
            throw e
        }
    }

}