package net.ljb.kt.client

import java.util.*

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

}