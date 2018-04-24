package com.ljb.mvp.kotlin.domain

/**
 * Created by L on 2017/9/14.
 */
data class Event(
        val id: String,
        val type: String,
        val actor: EventActor,
        val repo: EventRepo,
        val payload: EventPayLoad,
        val public: Boolean,
        val created_at: String
)


data class EventRepo(val id: String, val name: String, val url: String)

data class EventActor(val id: String, val login: String, val display_login: String, val gravatar_id: String, val url: String, val avatar_url: String)

data class EventPayLoad(val action: String?, val push_id: String?, val size: Int?, val distinct_size: String?, val ref: String?,
                        val head: String?, val before: String?, val commits: List<EventCommit>?)

data class EventCommit(val sha: String, val author: CommitAuthor, val message: String, val distinct: Boolean, val url: String)

data class CommitAuthor(val email: String, val name: String)
