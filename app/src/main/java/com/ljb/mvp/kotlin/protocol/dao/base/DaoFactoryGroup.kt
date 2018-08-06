package com.ljb.mvp.kotlin.protocol.dao.base

import java.util.*


class DaoFactoryGroup {

    private val map = WeakHashMap<Class<out DaoInterface>, DaoInterface>()

    fun register(key: Class<out DaoInterface>, value: DaoInterface) {
        if (key.isAssignableFrom(value::class.java)) map[key] = value
        else throw IllegalStateException("Dao interface register error ： value implements key ?")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : DaoInterface> getProtocol(key: Class<T>): T? {
        val protocol = map[key]
        return if (protocol == null) null
        else protocol as T
    }
}