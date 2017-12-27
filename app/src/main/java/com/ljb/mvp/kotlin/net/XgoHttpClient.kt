package com.ljb.rxjava.kotlin.net

import android.text.TextUtils
import com.ljb.rxjava.kotlin.log.XgoLog
import com.ljb.rxjava.kotlin.net.interceptor.XgoLogInterceptor
import okhttp3.*
import java.util.concurrent.TimeUnit


/**
 * Created by L on 2017/6/8.
 */

object XgoHttpClient {

    var GITHUB_CLIENT_ID = ""
    var GITHUB_CLIENT_SECRET = ""

    private val DEFAULT_TIME_OUT = 5000L

    val METHOD_GET: String = "GET"
    val METHOD_POST: String = "POST"
    val METHOD_PUT: String = "PUT"
    val METHOD_DELETE: String = "DELETE"


    private val mHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(XgoLogInterceptor(XgoLogInterceptor.Level.BODY) { XgoLog.d(it) })
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build()
    }


    /**
     * 同步
     * */
    fun execute(request: Request): Response = mHttpClient.newCall(request).execute()

    /**
     * 异步
     * */
    fun enqueue(request: Request, responseCallback: Callback) {
        mHttpClient.newCall(request).enqueue(responseCallback)
    }

    /**
     * 创建个Request
     * */
    fun getRequest(url: String, method: String, params: Map<String, String>?): Request {
        val builder = Request.Builder()
        if (METHOD_GET.equals(method, true)) {
            builder.url(initGetRequest(url, params)).get()
        } else if (METHOD_POST.equals(method, true)) {
            builder.url(url).post(initRequestBody(params))
        } else if (METHOD_PUT.equals(method, true)) {
            builder.url(url).put(initRequestBody(params))
        } else if (METHOD_DELETE.equals(method, true)) {
            if (params == null || params.isEmpty()) {
                builder.url(url).delete()
            } else {
                builder.url(url).delete(initRequestBody(params))
            }
        }
        return builder.build()
    }


    /**
     * 创建Body请求体
     * */
    private fun initRequestBody(params: Map<String, String>?): RequestBody {
        val bodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
        params!!.map {
            bodyBuilder.addFormDataPart(it.key, it.value)
        }
        //添加github授权
        if (!TextUtils.isEmpty(GITHUB_CLIENT_ID) && !TextUtils.isEmpty(GITHUB_CLIENT_SECRET)) {
            bodyBuilder.addFormDataPart("client_id", GITHUB_CLIENT_ID)
            bodyBuilder.addFormDataPart("client_secret", GITHUB_CLIENT_SECRET)
        }
        return bodyBuilder.build()
    }


    /**
     * 创建Get链接
     * */
    private fun initGetRequest(url: String, params: Map<String, String>?): String {
        var getUrl = url
        if (params != null && params.isNotEmpty()) {
            val sb = StringBuilder(url).append("?")
            var count = 0
            params.map {
                println("map")
                count++
                sb.append(it.key).append("=").append(it.value)
                if (count != params.size) {
                    sb.append("&")
                }
            }
            getUrl = sb.toString()
        }

        //添加github授权
        if (!TextUtils.isEmpty(GITHUB_CLIENT_ID) && !TextUtils.isEmpty(GITHUB_CLIENT_SECRET)) {
            getUrl += if (getUrl.contains("?")) "&" else "?"
            getUrl += "client_id=$GITHUB_CLIENT_ID&client_secret=$GITHUB_CLIENT_SECRET"
        }

        return getUrl
    }

}


