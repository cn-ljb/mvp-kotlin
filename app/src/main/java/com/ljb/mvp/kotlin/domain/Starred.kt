package com.ljb.mvp.kotlin.domain


/**
 * Created by L on 2017/9/21.
 */
data class Starred(val id: String, val name: String, val full_name: String, val owner: Owner, val html_url: String,
                   val stargazers_count: Int, val forks: Int, val open_issues_count: Int,
                   val created_at: String, val updated_at: String, val language: String)

data class Owner(val login: String, val id: String, val avatar_url: String)