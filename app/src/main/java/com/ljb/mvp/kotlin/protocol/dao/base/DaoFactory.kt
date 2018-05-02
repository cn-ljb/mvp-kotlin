package com.ljb.mvp.kotlin.protocol.dao.base

import com.ljb.mvp.kotlin.protocol.dao.IUserDao
import com.ljb.mvp.kotlin.protocol.dao.impl.UserDaoProtocol


object DaoFactory {

    private val mFactoryMap = DaoFactoryMap()

    init {
        //在此处注册Dao接口
        mFactoryMap.register(IUserDao::class.java, UserDaoProtocol)
    }

    fun <T : DaoInterface> getProtocol(clazz: Class<T>): T {
        return mFactoryMap.getProtocol(clazz)
    }

}