package com.wuba.weizhang.protocol.http

import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.utils.JsonParser
import com.ljb.rxjava.kotlin.net.XgoHttpClient
import com.ljb.rxjava.kotlin.protocol.BaseHttpProtocol
import com.wuba.weizhang.common.HTTP_API_DOMAIN
import io.reactivex.Observable

/**
 * Created by L on 2017/7/12.
 */
class UsersProtocol : BaseHttpProtocol() {

    fun loginForUserName(userName: String): Observable<User> {
        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}"
        return createObservable(url, XgoHttpClient.METHOD_GET) {
            JsonParser.getDefault().fromJson(it, User::class.java)
        }
    }

}