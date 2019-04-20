package com.ljb.mvp.kotlin.protocol.dao

import com.ljb.mvp.kotlin.BuildConfig
import com.ljb.mvp.kotlin.protocol.dao.impl.UserDaoProtocol
import dao.ljb.kt.core.IDaoProtocolConfig

/**
 * Author:Ljb
 * Time:2019/3/25
 * There is a lot of misery in life
 **/
class ProtocolConfig : IDaoProtocolConfig {

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    override fun <T> transformProtocol(clazz: Class<T>) = when (clazz) {
        IUserDaoProtocol::class.java -> UserDaoProtocol()
        else -> throw IllegalStateException("not found dao interface object  : ${clazz.name}")
    } as T

}
