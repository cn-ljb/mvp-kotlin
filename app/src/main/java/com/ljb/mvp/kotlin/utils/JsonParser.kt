package com.ljb.mvp.kotlin.utils

import com.google.gson.Gson

/**
 * Created by L on 2017/7/17.
 */
object JsonParser {

    val mGson by lazy { Gson() }
    val googleJsonParser by lazy { com.google.gson.JsonParser() }


    fun <T> fromJsonObj(json: String, clazz: Class<T>): T = mGson.fromJson(json, clazz)

    fun <T> fromJsonArr(json: String, clazz: Class<T>): MutableList<T> {
        val result = ArrayList<T>()
        val jsonArray = googleJsonParser.parse(json).asJsonArray
        jsonArray.mapTo(result) { mGson.fromJson(it, clazz) }
        return result
    }

}