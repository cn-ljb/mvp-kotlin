package com.ljb.mvp.kotlin.protocol.dao.base

import com.ljb.mvp.kotlin.protocol.dao.IUserDaoProtocol
import com.ljb.mvp.kotlin.protocol.dao.impl.UserDaoProtocol


object DaoFactory {

    private val mDaoGroup = DaoFactoryGroup()

    //TODO 在此处注册DAO接口
    @Suppress("UNCHECKED_CAST")
    private fun <T> getNewProtocol(clazz: Class<T>): T = when (clazz) {
        IUserDaoProtocol::class.java -> UserDaoProtocol
        else -> throw IllegalStateException("NotFound Dao Interface Object  : ${clazz.name}")
    } as T

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