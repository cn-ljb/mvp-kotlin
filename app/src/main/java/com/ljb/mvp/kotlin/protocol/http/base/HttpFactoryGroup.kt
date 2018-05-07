package com.ljb.mvp.kotlin.protocol.http.base


class HttpFactoryGroup {

    private val map = HashMap<Class<out HttpInterface>, HttpInterface>()

    fun register(key: Class<out HttpInterface>, value: HttpInterface) {
        if (key.isAssignableFrom(value::class.java)) map[key] = value
        else throw IllegalStateException("Http interface register error ： value implements key ?")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : HttpInterface> getProtocol(key: Class<T>): T? {
        val protocol = map[key]
        return if (protocol == null) null
        else protocol as T
    }
}