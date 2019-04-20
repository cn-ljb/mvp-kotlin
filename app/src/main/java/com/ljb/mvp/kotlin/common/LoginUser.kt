package com.ljb.mvp.kotlin.common

import com.ljb.mvp.kotlin.utils.SPUtils

/**
 * 用户登录信息
 * Author:Ljb
 * Time:2019/4/20
 * There is a lot of misery in life
 **/
object LoginUser {

    var uid: String
        get() = SPUtils.getString(Constant.SPKey.USER_ID)
        set(value) = SPUtils.putString(Constant.SPKey.USER_ID, value)

    var login: String
        get() = SPUtils.getString(Constant.SPKey.USER_LOGIN)
        set(value) = SPUtils.putString(Constant.SPKey.USER_LOGIN, value)

    var name: String
        get() = SPUtils.getString(Constant.SPKey.USER_NAME)
        set(value) = SPUtils.putString(Constant.SPKey.USER_NAME, value)

    var img: String
        get() = SPUtils.getString(Constant.SPKey.USER_IMG)
        set(value) = SPUtils.putString(Constant.SPKey.USER_IMG, value)

    fun clear() {
        login = ""
        uid = ""
        name = ""
        img = ""
    }
}
