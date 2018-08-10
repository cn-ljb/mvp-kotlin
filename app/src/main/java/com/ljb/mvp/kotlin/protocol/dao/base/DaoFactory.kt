package com.ljb.mvp.kotlin.protocol.dao.base

import com.ljb.mvp.kotlin.protocol.dao.DaoFactoryConfig


object DaoFactory {

    private val mDaoGroup = DaoFactoryGroup()

    private fun <T> getNewProtocol(clazz: Class<T>): T = DaoFactoryConfig.configProtocol(clazz)

    @Suppress("UNCHECKED_CAST")
    fun <T : DaoInterface> getProtocol(clazz: Class<T>): T {
        return mDaoGroup.getProtocol(clazz) ?: registerNewProtocol(clazz)
    }

    private fun <T : DaoInterface> registerNewProtocol(clazz: Class<T>): T {
        val protocol = getNewProtocol(clazz)
        mDaoGroup.register(clazz, protocol)
        return protocol
    }

}