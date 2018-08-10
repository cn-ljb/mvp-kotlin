package com.ljb.mvp.kotlin.protocol.dao

import android.content.Context
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.dao.base.DaoInterface
import io.reactivex.Observable

interface IUserDaoProtocol : DaoInterface {
    /**
     * 插入新用户信息
     * */
    fun insertUser(context: Context, user: User): Observable<Boolean>

    /**
     * 更新用户信息
     * */
    fun updateUser(context: Context, user: User): Observable<Boolean>

    /**
     * 通过用户id查询用户信息
     * */
    fun findUserByUserId(context: Context, userId: Long): Observable<User?>

    /**
     * 通过用户名查询用户信息
     * */
    fun findUserByName(context: Context, userName: String): Observable<User?>

    /**
     * 保存用户
     * */
    fun saveUser(context: Context, user: User): Observable<Boolean>
}