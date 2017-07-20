package com.ljb.mvp.kotlin.common

import android.provider.BaseColumns

/**
 * 常量池
 * Created by L on 2017/7/11.
 */
class Constant {

    /**
     *  SharedPreferences常量池
     * */
    class SPConstant {
        companion object {
            val CUR_USER_NAME = "user_name"
        }
    }


    /**
     * 数据库常量池
     * */
    class DBProvider {

        companion object {
            val AUTHORITY = "com.ljb.mvp.kotlin.provider"
            val DATABASE_NAME = "mvpkotlindb"
            val DATABASE_VERSION = 1
        }

        //用户表
        class TABLE_USERS {
            companion object {
                val TABLE_NAME = "table_users"
                val COLUMN_ID = BaseColumns._ID
                val COLUMN_LOGIN= "login"
                val COLUMN_USER_ID = "user_id"
                val COLUMN_AVATAR_URL = "avatar_url"
                val COLUMN_GRAVATAR_ID = "gravatar_id"
                val COLUMN_URL = "url"
                val COLUMN_HTML_URL = "html_url"
                val COLUMN_FOLLOWERS_URL = "followers_url"
                val COLUMN_FOLLOWING_URL = "following_url"
                val COLUMN_GISTS_URL = "gists_url"
                val COLUMN_STARRED_URL = "starred_url"
                val COLUMN_SUBSCRIPTIONS_URL = "subscriptions_url"
                val COLUMN_ORGANIZATIONS_URL = "organizations_url"
                val COLUMN_REPOS_URL = "repos_url"
                val COLUMN_EVENTS_URL = "events_url"
                val COLUMN_RECEIVED_EVENTS_URL = "received_events_url"
                val COLUMN_TYPE = "type"
                val COLUMN_SITE_ADMIN = "site_admin"
                val COLUMN_NAME = "name"
                val COLUMN_COMPANY = "company"
                val COLUMN_BLOG = "blog"
                val COLUMN_LOCATION = "location"
                val COLUMN_EMAIL = "email"
                val COLUMN_HIREABLE = "hireable"
                val COLUMN_BIO = "bio"
                val COLUMN_PUBLIC_REPOS = "public_repos"
                val COLUMN_PUBLIC_GISTS = "public_gists"
                val COLUMN_FOLLOWERS = "followers"
                val COLUMN_FOLLOWING = "following"
                val COLUMN_CREATED_AT = "created_at"
                val COLUMN_UPDATED_AT = "updated_at"
            }
        }
    }

}