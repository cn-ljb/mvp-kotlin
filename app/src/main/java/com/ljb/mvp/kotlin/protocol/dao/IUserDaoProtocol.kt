package com.ljb.mvp.kotlin.protocol.dao

import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.table.UserTable
import dao.ljb.kt.core.IDaoInterface
import io.reactivex.Observable

/**
 * Author:Ljb
 * Time:2019/4/20
 * There is a lot of misery in life
 **/
interface IUserDaoProtocol : IDaoInterface {
    /**
     * 通过用户id查询用户信息
     * */
    fun queryUserByUserId(table: UserTable, userId: String): Observable<User>

    /**
     * 保存用户
     * */
    fun saveUser(table: UserTable, user: User): Observable<Boolean>
}