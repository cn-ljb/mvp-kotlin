package com.wuba.weizhang.common

import com.ljb.mvp.kotlin.common.Constant
import com.ljb.rxjava.kotlin.log.XgoLog.Companion.LEVEL_ALL
import com.ljb.mvp.kotlin.utils.SPUtils


/** 服务器Host */
const val HTTP_API_DOMAIN = "https://api.github.com"

/** Log级别 */
const val LV_LOG = LEVEL_ALL

var GITHUB_CLIENT_ID = ""
var GITHUB_CLIENT_SECRET = ""

/** 用户登录信息 */
class LoginUser {
    companion object {
        var name: String
            get() = SPUtils.getString(Constant.SPConstant.CUR_USER_NAME)
            set(value) = SPUtils.putString(Constant.SPConstant.CUR_USER_NAME, value)
    }
}

