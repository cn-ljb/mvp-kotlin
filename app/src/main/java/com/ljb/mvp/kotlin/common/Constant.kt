package com.ljb.mvp.kotlin.common

import com.ljb.mvp.kotlin.BuildConfig

/**
 * 常量池
 * Created by L on 2017/7/11.
 */
object Constant {

    const val APP_ID_BUGLY = "733873bc89"

    /** 服务器Host */
    const val HTTP_API_DOMAIN = "https://api.github.com"

    /** GitHub接口身份验证 */
    const val GITHUB_CLIENT_ID = ""
    const val GITHUB_CLIENT_SECRET = ""

    /**
     *  SharedPreferences常量池
     * */
    object SPKey {
        const val USER_LOGIN = "user_login"
        const val USER_NAME = "user_name"
        const val USER_ID = "user_id"
        const val USER_IMG = "user_img"
    }

    /**
     * 数据库常量池
     * */
    object DB {
        const val NAME = "db_${BuildConfig.APPLICATION_ID}"
        const val VERSION = 1
    }

    /**
     * 权限Code
     * */
    object PermissionCode {
        const val CODE_INIT = 0x00
    }

}