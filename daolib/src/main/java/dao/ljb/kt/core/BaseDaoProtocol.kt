package dao.ljb.kt.core

import dao.ljb.kt.db.DatabaseOpenHelper
import io.reactivex.Observable

/**
 * Author:Ljb
 * Time:2018/11/9
 * There is a lot of misery in life
 **/
abstract class BaseDaoProtocol {

    protected val mSqliteDb by lazy { DatabaseOpenHelper.getDefInstance().writableDatabase }

    fun <T> createObservable(f: () -> T): Observable<T> {
        return Observable.create {
            try {
                val result: T = f()
                it.onNext(result)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}