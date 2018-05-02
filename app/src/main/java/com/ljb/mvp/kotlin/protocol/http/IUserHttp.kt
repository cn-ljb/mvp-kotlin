package com.ljb.mvp.kotlin.protocol.http

import com.ljb.mvp.kotlin.domain.*
import com.ljb.mvp.kotlin.protocol.http.base.HttpInterface
import io.reactivex.Observable

interface IUserHttp : HttpInterface {
    /**
     * @param userName 用户名
     * @return  用户基本信息
     * */
    fun getUserInfoByName(userName: String): Observable<User>

    /**
     * @param userName 用户名
     * @param page 页码
     * @return Events列表数据
     * */
    fun getEventsByName(userName: String, page: Int): Observable<MutableList<Event>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @return Starred列表数据
     * */
    fun getStarredByName(userName: String, page: Int): Observable<MutableList<Starred>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @returnt Followers列表数据
     * */
    fun getFollowersByName(userName: String, page: Int): Observable<MutableList<Follower>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @returnt Repositories列表数据
     * */
    fun getRepositoriesByName(userName: String, page: Int): Observable<MutableList<Repository>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @returnt Following列表数据
     * */
    fun getFollowingByName(userName: String, page: Int): Observable<MutableList<Following>>
}