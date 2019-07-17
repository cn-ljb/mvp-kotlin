package com.ljb.mvp.kotlin.protocol.http

import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.domain.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Author:Ljb
 * Time:2018/8/15
 * There is a lot of misery in life
 **/

interface IReposHttpProtocol {
    /**
     * @param url ReposUrl
     * @return Repos信息
     * */
    @GET
    fun getReposFromUrl(@Url url: String): Observable<Repository>
}