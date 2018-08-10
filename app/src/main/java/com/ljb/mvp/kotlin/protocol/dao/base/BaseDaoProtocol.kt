package com.ljb.mvp.kotlin.protocol.dao.base

import io.reactivex.Observable

/**
 * Created by L on 2017/7/11.
 */
abstract class BaseDaoProtocol {

    fun <T> createObservable(f: () -> T): Observable<T> {
        return Observable.create {
            val result: T = f()
            it.onNext(result)
            it.onComplete()
        }
    }
}