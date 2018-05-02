package com.ljb.mvp.kotlin.protocol.http.base


class HttpFactoryMap {

    private val map = HashMap<Class<out HttpInterface>, BaseHttpProtocol>()

    fun register(key: Class<out HttpInterface>, value: BaseHttpProtocol) {
        if (key.isAssignableFrom(value::class.java)) map[key] = value
        else throw IllegalStateException("Http interface register error ： value implements key ?")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : HttpInterface> getProtocol(key: Class<T>): T {
        val protocol = map[key] ?: throw IllegalStateException("Http interface unregistered")
        if (key.isAssignableFrom(protocol::class.java)) return protocol as T
        else throw IllegalStateException("Http interface register error ： value implements key ?")
    }
}