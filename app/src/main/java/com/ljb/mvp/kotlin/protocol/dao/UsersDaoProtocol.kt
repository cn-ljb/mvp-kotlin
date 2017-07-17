package com.ljb.mvp.kotlin.protocol.dao

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import com.ljb.mvp.kotlin.common.Constant.DBProvider.TABLE_USERS
import com.ljb.mvp.kotlin.domain.User
import com.ljb.rxjava.kotlin.log.XgoLog
import com.ljb.mvp.kotlin.db.DatabaseProvider
import com.wuba.weizhang.protocol.base.BaseDAOProtocol

/**
 * Created by L on 2017/7/17.
 */
class UsersDaoProtocol(c: Context) : BaseDAOProtocol(c) {


    fun saveUser(user: User): Boolean {
        //保存用户
        val values = ContentValues()
        values.put(TABLE_USERS.COLUMN_LOGIN, user.login)
        values.put(TABLE_USERS.COLUMN_USER_ID, user.id)
        values.put(TABLE_USERS.COLUMN_AVATAR_URL, user.avatar_url)
        values.put(TABLE_USERS.COLUMN_GRAVATAR_ID, user.gravatar_id)
        values.put(TABLE_USERS.COLUMN_URL, user.url)
        values.put(TABLE_USERS.COLUMN_HTML_URL, user.html_url)
        values.put(TABLE_USERS.COLUMN_FOLLOWERS_URL, user.followers_url)
        values.put(TABLE_USERS.COLUMN_FOLLOWING_URL, user.following_url)
        values.put(TABLE_USERS.COLUMN_GISTS_URL, user.gists_url)
        values.put(TABLE_USERS.COLUMN_STARRED_URL, user.starred_url)
        values.put(TABLE_USERS.COLUMN_SUBSCRIPTIONS_URL, user.subscriptions_url)
        values.put(TABLE_USERS.COLUMN_ORGANIZATIONS_URL, user.organizations_url)
        values.put(TABLE_USERS.COLUMN_REPOS_URL, user.repos_url)
        values.put(TABLE_USERS.COLUMN_EVENTS_URL, user.events_url)
        values.put(TABLE_USERS.COLUMN_RECEIVED_EVENTS_URL, user.received_events_url)
        values.put(TABLE_USERS.COLUMN_TYPE, user.type)
        values.put(TABLE_USERS.COLUMN_SITE_ADMIN, if (user.site_admin) 1 else 0)
        values.put(TABLE_USERS.COLUMN_NAME, user.name)
        values.put(TABLE_USERS.COLUMN_COMPANY, user.company)
        values.put(TABLE_USERS.COLUMN_BLOG, user.blog)
        values.put(TABLE_USERS.COLUMN_LOCATION, user.location)
        values.put(TABLE_USERS.COLUMN_EMAIL, user.email)
        values.put(TABLE_USERS.COLUMN_HIREABLE, user.hireable)
        values.put(TABLE_USERS.COLUMN_BIO, user.bio)
        values.put(TABLE_USERS.COLUMN_PUBLIC_REPOS, user.public_repos)
        values.put(TABLE_USERS.COLUMN_PUBLIC_GISTS, user.public_gists)
        values.put(TABLE_USERS.COLUMN_FOLLOWERS, user.followers)
        values.put(TABLE_USERS.COLUMN_FOLLOWING, user.following)
        values.put(TABLE_USERS.COLUMN_CREATED_AT, user.created_at)
        values.put(TABLE_USERS.COLUMN_UPDATED_AT, user.updated_at)
        try {
            val uri = mContext.contentResolver.insert(Uri.parse(DatabaseProvider.USER_CONTENT_URI), values)
            if (uri != null && ContentUris.parseId(uri) > 0) {
                return true
            }
        } catch (e: Exception) {
            XgoLog.e("save user error ${user.login}")
            e.printStackTrace()
        }
        return false
    }

}