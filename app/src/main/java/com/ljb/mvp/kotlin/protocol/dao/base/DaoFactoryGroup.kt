package com.ljb.mvp.kotlin.protocol.dao.base

import java.util.*


class DaoFactoryGroup {

    private val map = WeakHashMap<Class<out DaoInterface>, BaseDaoProtocol>()

    fun register(key: Class<out DaoInterface>, value: BaseDaoProtocol) {
        if (key.isAssignableFrom(value::class.java)) map[key] = value
        else throw IllegalStateException("DAO interface register error ： value implements key ?")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : DaoInterface> getProtocol(key: Class<T>): T {
        val protocol = map[key] ?: throw IllegalStateException("DAO interface unregistered")
        if (key.isAssignableFrom(protocol::class.java)) return protocol as T
        else throw IllegalStateException("DAO interface register error ： value implements key ?")
    }
}