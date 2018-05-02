package com.ljb.mvp.kotlin.protocol.dao

import android.content.Context
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.dao.base.DaoInterface

interface IUserDao : DaoInterface {
    /**
     * 保存用户信息
     * */
    fun saveUser(context: Context, user: User): Boolean

    /**
     * 更新用户信息
     * */
    fun updateUser(context: Context, user: User): Int

    /**
     * 通过用户id查询用户信息
     * */
    fun findUserByUserId(context: Context, userId: Long): User?

    /**
     * 通过用户名查询用户信息
     * */
    fun findUserByName(context: Context, userName: String): User?
}