package com.ljb.mvp.kotlin.protocol.dao.base



class DaoFactoryGroup {

    private val map = HashMap<Class<out DaoInterface>, BaseDaoProtocol>()

    fun register(key: Class<out DaoInterface>, value: BaseDaoProtocol) {
        if (key.isAssignableFrom(value::class.java)) map[key] = value
        else throw IllegalStateException("Http interface register error ： value implements key ?")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : DaoInterface> getProtocol(key: Class<T>): T {
        val protocol = map[key] ?: throw IllegalStateException("Http interface unregistered")
        if (key.isAssignableFrom(protocol::class.java)) return protocol as T
        else throw IllegalStateException("Http interface register error ： value implements key ?")
    }
}