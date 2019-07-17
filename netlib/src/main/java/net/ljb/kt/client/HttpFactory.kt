package net.ljb.kt.client

import android.util.Log
import java.util.*

/**
 * WBHttpClient
 * 1、可使用 OkHttp
 * 2、也可使用 Retrofit
 * Created by L on 2017/6/8.
 */
object HttpFactory {

    private val mProtocolMap = WeakHashMap<Class<*>, Any>()
    private val mStrProtocolMap = WeakHashMap<Class<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T> getProtocol(clazz: Class<T>, isStrClient: Boolean = false): T {
        val map = if (isStrClient) mStrProtocolMap else mProtocolMap
        return if (map.contains(clazz)) {
            map[clazz] as T
        } else {
            val protocol = if (isStrClient) {
                HttpClient.getStrRetrofit().create(clazz)
            } else {
                HttpClient.getRetrofit().create(clazz)
            }
            map[clazz] = protocol
            protocol
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getProtocol(clazz: Class<T>): T {
        Log.i("XLog", "protocol size : ${mProtocolMap.size}")
        return if (mProtocolMap.contains(clazz)) {
            mProtocolMap[clazz] as T
        } else {
            val protocol = HttpClient.getRetrofit().create(clazz)
            mProtocolMap[clazz] = protocol
            protocol
        }
    }
}