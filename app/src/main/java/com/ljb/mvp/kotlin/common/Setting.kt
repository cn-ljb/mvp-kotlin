package com.wuba.weizhang.common

import com.ljb.mvp.kotlin.common.Constant
import com.wuba.weizhang.utils.SPUtils

/**
 * 项目相关配置
 * Created by L on 2017/7/12.
 */


class LoginUser {
    companion object {
        var name: String
            get() = SPUtils.getString(Constant.SPConstant.CUR_USER_NAME)
            set(value) = SPUtils.putString(Constant.SPConstant.CUR_USER_NAME, value)
    }
}

/**
 * 服务器Host
 * */
val HTTP_API_DOMAIN = "https://api.github.com"

/** 是否允许输出log
 * -1：  不允许
 * 其他：根据等级允许（10 全部允许）
 */
val LV_LOG = 10

