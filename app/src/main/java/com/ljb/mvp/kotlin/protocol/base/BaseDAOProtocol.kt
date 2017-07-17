package com.wuba.weizhang.protocol.base

import android.content.Context
import io.reactivex.Observable

/**
 * Created by L on 2017/7/11.
 */
abstract class BaseDAOProtocol(val mContext: Context) {

    fun <T> createObservable(f: () -> T): Observable<T> {
        return Observable.create {
            val result: T = f()
            if (result != null) {
                it.onNext(result)
                it.onComplete()
            } else {
                it.onError(Throwable("dao not data"))
            }
        }
    }
}