package com.ljb.mvp.kotlin.net

import android.text.TextUtils
import com.ljb.mvp.kotlin.common.GITHUB_CLIENT_ID
import com.ljb.mvp.kotlin.common.GITHUB_CLIENT_SECRET
import com.ljb.mvp.kotlin.net.interceptor.XgoLogInterceptor
import com.ljb.mvp.kotlin.net.log.XgoLog
import okhttp3.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager


/**
 * Created by L on 2017/6/8.
 */
object XgoHttpClient {

    private const val DEFAULT_TIME_OUT = 5000L

    const val METHOD_GET: String = "GET"
    const val METHOD_POST: String = "POST"
    const val METHOD_PUT: String = "PUT"
    const val METHOD_DELETE: String = "DELETE"


    private val mHttpClient by lazy {
        OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier { _, _ -> true }
                .addInterceptor(XgoLogInterceptor(XgoLogInterceptor.Level.BODY) { XgoLog.d(it) })
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build()
    }

    private fun createSSLSocketFactory(): SSLSocketFactory? {
        var ssfFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("SSL")
            sc.init(null, arrayOf(TrustAllCerts()), java.security.SecureRandom())
            ssfFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ssfFactory
    }

    private class TrustAllCerts : X509TrustManager {

        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate?> {
            return arrayOfNulls(0)
        }
    }


    /**
     * 同步
     * */
    fun execute(request: Request): Response = mHttpClient.newCall(request).execute()

    /**
     * 异步
     * */
    fun enqueue(request: Request, responseCallback: Callback) = mHttpClient.newCall(request).enqueue(responseCallback)

    /**
     * 创建Request
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
        var getUrl = StringBuilder(url).apply {
            if (params != null && params.isNotEmpty()) {
                append("?")
                params.map {
                    append(it.key).append("=").append(it.value).append("&")
                }
                delete(this.length - "&".length, this.length)
            }
        }.toString()

        //添加github授权
        if (!TextUtils.isEmpty(GITHUB_CLIENT_ID) && !TextUtils.isEmpty(GITHUB_CLIENT_SECRET)) {
            getUrl += if (getUrl.contains("?")) "&" else "?"
            getUrl += "client_id=$GITHUB_CLIENT_ID&client_secret=$GITHUB_CLIENT_SECRET"
        }
        return getUrl
    }

}


