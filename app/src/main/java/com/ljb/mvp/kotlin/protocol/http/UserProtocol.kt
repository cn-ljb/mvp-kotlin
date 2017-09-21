package com.wuba.weizhang.protocol.http

import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.domain.Starred
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.utils.JsonParser
import com.ljb.rxjava.kotlin.net.XgoHttpClient
import com.ljb.rxjava.kotlin.protocol.BaseHttpProtocol
import com.wuba.weizhang.common.HTTP_API_DOMAIN
import io.reactivex.Observable

/**
 * Created by L on 2017/7/12.
 */
object UserProtocol : BaseHttpProtocol() {

    fun getUserInfoByName(userName: String): Observable<User> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}"
        return createObservable(url, XgoHttpClient.METHOD_GET) {
            JsonParser.fromJsonObj(it, User::class.java)
        }
    }


    fun getEventsByName(userName: String, page: Int): Observable<MutableList<Event>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/events"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Event::class.java)
        }
    }

    fun getStarredByName(userName: String, page: Int): Observable<MutableList<Starred>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/starred"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Starred::class.java)
        }
    }
}