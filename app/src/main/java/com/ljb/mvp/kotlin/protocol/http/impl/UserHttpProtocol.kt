package com.ljb.mvp.kotlin.protocol.http.impl

import com.ljb.mvp.kotlin.domain.*
import com.ljb.mvp.kotlin.utils.JsonParser
import com.ljb.mvp.kotlin.net.XgoHttpClient
import com.ljb.mvp.kotlin.protocol.http.base.BaseHttpProtocol
import com.ljb.mvp.kotlin.common.HTTP_API_DOMAIN
import com.ljb.mvp.kotlin.protocol.http.IUserHttp
import io.reactivex.Observable

/**
 * Created by L on 2017/7/12.
 */
object UserHttpProtocol : BaseHttpProtocol(), IUserHttp {

    override fun getUserInfoByName(userName: String): Observable<User> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}"
        return createObservable(url, XgoHttpClient.METHOD_GET) {
            JsonParser.fromJsonObj(it, User::class.java)
        }
    }

    override fun getEventsByName(userName: String, page: Int): Observable<MutableList<Event>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/events"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Event::class.java)
        }
    }

    override fun getStarredByName(userName: String, page: Int): Observable<MutableList<Starred>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/starred"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Starred::class.java)
        }
    }


    override fun getFollowersByName(userName: String, page: Int): Observable<MutableList<Follower>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/followers"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Follower::class.java)
        }
    }


    override fun getRepositoriesByName(userName: String, page: Int): Observable<MutableList<Repository>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/repos"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Repository::class.java)
        }
    }

    override fun getFollowingByName(userName: String, page: Int): Observable<MutableList<Following>> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}/following"
        return createObservable(url, XgoHttpClient.METHOD_GET, mapOf("page" to "$page")) {
            JsonParser.fromJsonArr(it, Following::class.java)
        }
    }

}