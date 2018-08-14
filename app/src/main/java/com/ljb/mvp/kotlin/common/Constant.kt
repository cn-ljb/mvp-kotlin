package com.ljb.mvp.kotlin.common

import android.provider.BaseColumns

/**
 * 常量池
 * Created by L on 2017/7/11.
 */
object Constant {

    /**
     *  SharedPreferences常量池
     * */
    object SPConstant {
        val CUR_USER_NAME = "user_name"
    }

    object WEB {
        val SCHEME = "KotlinMVP"
    }


    /**
     * 数据库常量池
     * */
    object DBProvider {

        const val AUTHORITY = "com.ljb.mvp.kotlin.provider"
        const val DATABASE_NAME = "mvpkotlindb"
        const val DATABASE_VERSION = 1

        //用户表
        object TABLE_USERS {
            const val TABLE_NAME = "table_users"
            const val COLUMN_ID = BaseColumns._ID
            const val COLUMN_LOGIN = "login"
            const val COLUMN_USER_ID = "user_id"
            const val COLUMN_AVATAR_URL = "avatar_url"
            const val COLUMN_GRAVATAR_ID = "gravatar_id"
            const val COLUMN_URL = "url"
            const val COLUMN_HTML_URL = "html_url"
            const val COLUMN_FOLLOWERS_URL = "followers_url"
            const val COLUMN_FOLLOWING_URL = "following_url"
            const val COLUMN_GISTS_URL = "gists_url"
            const val COLUMN_STARRED_URL = "starred_url"
            const val COLUMN_SUBSCRIPTIONS_URL = "subscriptions_url"
            const val COLUMN_ORGANIZATIONS_URL = "organizations_url"
            const val COLUMN_REPOS_URL = "repos_url"
            const val COLUMN_EVENTS_URL = "events_url"
            const val COLUMN_RECEIVED_EVENTS_URL = "received_events_url"
            const val COLUMN_TYPE = "type"
            const val COLUMN_SITE_ADMIN = "site_admin"
            const val COLUMN_NAME = "name"
            const val COLUMN_COMPANY = "company"
            const val COLUMN_BLOG = "blog"
            const val COLUMN_LOCATION = "location"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_HIREABLE = "hireable"
            const val COLUMN_BIO = "bio"
            const val COLUMN_PUBLIC_REPOS = "public_repos"
            const val COLUMN_PUBLIC_GISTS = "public_gists"
            const val COLUMN_FOLLOWERS = "followers"
            const val COLUMN_FOLLOWING = "following"
            const val COLUMN_CREATED_AT = "created_at"
            const val COLUMN_UPDATED_AT = "updated_at"
        }
    }

}