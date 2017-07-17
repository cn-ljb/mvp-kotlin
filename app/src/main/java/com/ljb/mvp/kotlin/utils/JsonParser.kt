package com.ljb.mvp.kotlin.utils

import com.google.gson.Gson

/**
 * Created by L on 2017/7/17.
 */
object JsonParser {

    private val defaultParser by lazy { Gson() }

    fun getDefault() = defaultParser

}