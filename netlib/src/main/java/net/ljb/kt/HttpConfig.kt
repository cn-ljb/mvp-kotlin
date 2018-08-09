package net.ljb.kt

import net.ljb.kt.utils.NetLog

object HttpConfig {
    private var mHeaderMap: Map<String, String>? = null
    private var mParamMap: Map<String, String>? = null
    private var mBaseUrl: String = ""
    private var isLog: Boolean = false

    fun init(baseUrl: String, headers: Map<String, String>? = null, params: Map<String, String>? = null, isLog: Boolean = false) {
        setBaseUrl(baseUrl)
        headers?.apply { setHeader(this) }
        params?.apply { setParam(this) }

    }

    fun setBaseUrl(url: String) {
        mBaseUrl = url
    }

    fun getBaseUrl(): String {
        return checkNotNull(mBaseUrl)
    }

    fun setHeader(map: Map<String, String>) {
        mHeaderMap = map
    }

    fun setParam(map: Map<String, String>) {
        mParamMap = map
    }

    fun getHeader(): Map<String, String>? {
        return mHeaderMap
    }

    fun getParam(): Map<String, String>? {
        return mParamMap
    }

    fun setLogEnable(isLog: Boolean) {
        HttpConfig.isLog = isLog
    }

    fun getLogLv(): Int {
        return if (isLog) NetLog.LEVEL_ALL else NetLog.LEVEL_NONE
    }

}