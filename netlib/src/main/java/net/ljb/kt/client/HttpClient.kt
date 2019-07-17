@file:Suppress("DEPRECATION")

package net.ljb.kt.client

import net.ljb.kt.HttpConfig
import net.ljb.kt.interceptor.AddGlobalParamInterceptor
import net.ljb.kt.utils.NetLog
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager


/**
 * HttpClient
 * 1、可使用 OkHttp
 * 2、也可使用 Retrofit
 * Created by L on 2017/6/8.
 */
object HttpClient {

    private const val DEFAULT_TIME_OUT = 3000L * 10
    private const val DEFAULT_DOWN_TIME_OUT = 1000L * 60 * 3

    private val mRetrofit by lazy {
        Retrofit.Builder()
                .client(mHttpClient)
                .baseUrl(HttpConfig.getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val mStrRetrofit by lazy {
        Retrofit.Builder()
                .client(mHttpClient)
                .baseUrl(HttpConfig.getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory())
                .build()
    }

    private val mHttpClient by lazy {
        val logInterceptor = HttpLoggingInterceptor { NetLog.d(it) }
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier { _, _ -> true }
                .addInterceptor(logInterceptor)
                .addInterceptor(AddGlobalParamInterceptor())
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build()
    }

    private val mLongTimeHttpClient by lazy {
        OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier { _, _ -> true }
                .connectTimeout(DEFAULT_DOWN_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_DOWN_TIME_OUT, TimeUnit.MILLISECONDS)
                .build()
    }

    private fun createSSLSocketFactory(): SSLSocketFactory {
        val sc = SSLContext.getInstance("SSL")
        sc.init(null, arrayOf(TrustAllCerts()), java.security.SecureRandom())
        return sc.socketFactory
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

    fun getHttpClient(): OkHttpClient = mHttpClient

    fun getLongHttpClient() = mLongTimeHttpClient

    /**
     * Retrofit
     * */
    fun getRetrofit(): Retrofit = mRetrofit

    /**
     * StrRetrofit
     * */
    fun getStrRetrofit(): Retrofit = mStrRetrofit

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
    fun getRequest(url: String, method: HttpMethod, params: Map<String, String>?): Request {
        val builder = Request.Builder()
        if (HttpMethod.GET == method) {
            builder.url(initGetRequest(url, params ?: HashMap())).get()
        } else if (HttpMethod.POST == method) {
            builder.url(url).post(initRequestBody(params ?: HashMap()))
        } else if (HttpMethod.PUT == method) {
            builder.url(url).put(initRequestBody(params ?: HashMap()))
        } else if (HttpMethod.DELETE == method) {
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
    private fun initRequestBody(params: Map<String, String>): RequestBody {
        val bodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
        params.map { bodyBuilder.addFormDataPart(it.key, it.value) }
        return bodyBuilder.build()
    }


    /**
     * 创建Get链接
     * */
    private fun initGetRequest(url: String, params: Map<String, String>): String {
        return StringBuilder(url).apply {
            if (params.isNotEmpty()) {
                append("?")
                params.map {
                    append(it.key).append("=").append(it.value).append("&")
                }
                delete(length - "&".length, length)
            }
        }.toString()
    }


}


