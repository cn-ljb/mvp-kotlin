package com.ljb.mvp.kotlin.protocol.http

import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.utils.JsonParser
import com.ljb.rxjava.kotlin.net.XgoHttpClient
import com.ljb.rxjava.kotlin.protocol.BaseHttpProtocol
import com.wuba.weizhang.common.HTTP_API_DOMAIN
import com.wuba.weizhang.protocol.http.UsersProtocol
import io.reactivex.Observable

/**
 * Created by L on 2017/9/14.
 */
object EventProtocol : BaseHttpProtocol() {

    fun getEventsByName(userName: String): Observable<List<Event>> {
        val url = "$HTTP_API_DOMAIN/users/${UsersProtocol.nvl(userName)}/events"
        return UsersProtocol.createObservable(url, XgoHttpClient.METHOD_GET) {
            JsonParser.fromJsonArr(it, Event::class.java)
        }
    }
}