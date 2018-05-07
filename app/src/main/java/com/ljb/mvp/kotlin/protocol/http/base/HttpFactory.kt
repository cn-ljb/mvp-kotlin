package com.ljb.mvp.kotlin.protocol.http.base

import com.ljb.mvp.kotlin.protocol.http.IUserHttp
import com.ljb.mvp.kotlin.protocol.http.impl.UserHttpProtocol


object HttpFactory {

    private val mHttpGroup = HttpFactoryGroup()

    @Suppress("UNCHECKED_CAST")
    fun <T : HttpInterface> getProtocol(clazz: Class<T>): T {
        return mHttpGroup.getProtocol(clazz) ?: registerNewProtocol(clazz)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : HttpInterface> registerNewProtocol(clazz: Class<T>): T {
        //TODO 在此处注册Http接口
        val protocol = when (clazz) {
            IUserHttp::class.java -> UserHttpProtocol
            else -> throw IllegalStateException("Http Interface Class Object NotFound : ${clazz.name}")
        } as T
        mHttpGroup.register(clazz, protocol)
        return protocol
    }
}