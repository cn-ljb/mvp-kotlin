package com.ljb.mvp.kotlin.protocol.dao.base

import com.ljb.mvp.kotlin.protocol.dao.IUserDao
import com.ljb.mvp.kotlin.protocol.dao.impl.UserDaoProtocol


object DaoFactory {

    private val mDaoGroup = DaoFactoryGroup()

    init {
        //TODO 在此处注册Dao接口
        mDaoGroup.register(IUserDao::class.java, UserDaoProtocol)
    }

    fun <T : DaoInterface> getProtocol(clazz: Class<T>): T {
        return mDaoGroup.getProtocol(clazz)
    }

}