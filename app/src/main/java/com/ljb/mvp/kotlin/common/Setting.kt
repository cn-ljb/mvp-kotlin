package com.ljb.mvp.kotlin.common

import com.ljb.mvp.kotlin.BuildConfig
import com.ljb.mvp.kotlin.utils.SPUtils


/** 服务器Host */
const val HTTP_API_DOMAIN = "https://api.github.com"

/** Log */
val LOG_DEBUG = BuildConfig.DEBUG

/** GitHub接口身份验证 */
var GITHUB_CLIENT_ID = "c68ea892fdebf06c418b"
var GITHUB_CLIENT_SECRET = "e2cf54c4dd27c702ca1d218ff87cbfc8fc6daccf"

/** 用户登录信息 */
class LoginUser {
    companion object {
        var name: String
            get() = SPUtils.getString(Constant.SPConstant.CUR_USER_NAME)
            set(value) = SPUtils.putString(Constant.SPConstant.CUR_USER_NAME, value)
    }
}

