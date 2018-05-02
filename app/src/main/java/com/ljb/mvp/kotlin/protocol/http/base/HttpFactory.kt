package com.ljb.mvp.kotlin.protocol.http.base

import com.ljb.mvp.kotlin.protocol.http.IUserHttp
import com.ljb.mvp.kotlin.protocol.http.impl.UserHttpProtocol

object HttpFactory {

    private val mFactoryMap = HttpFactoryMap()

    init {
        //在此处注册Http接口
        mFactoryMap.register(IUserHttp::class.java, UserHttpProtocol)
    }

    fun <T : HttpInterface> getProtocol(clazz: Class<T>): T {
        return mFactoryMap.getProtocol(clazz)
    }

}