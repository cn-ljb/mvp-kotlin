package dao.ljb.kt.core

import java.util.*

/**
 * Author:Ljb
 * Time:2018/11/9
 * There is a lot of misery in life
 **/
class DaoProtocolGroup {

    private val map = WeakHashMap<Class<out IDaoInterface>, IDaoInterface>()

    fun register(key: Class<out IDaoInterface>, value: IDaoInterface) {
        if (key.isAssignableFrom(value::class.java)) map[key] = value
        else throw IllegalStateException("Dao interface register error ： value implements key ?")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : IDaoInterface> getProtocol(key: Class<T>): T? {
        val protocol = map[key]
        return if (protocol == null) null
        else protocol as T
    }
}