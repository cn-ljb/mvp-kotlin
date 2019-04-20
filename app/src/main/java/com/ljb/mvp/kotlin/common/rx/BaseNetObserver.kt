package com.ljb.mvp.kotlin.common.rx

import android.content.Context
import android.widget.Toast
import com.ljb.mvp.kotlin.domain.GitHubHttpError
import com.ljb.mvp.kotlin.utils.JsonParser

import com.ljb.mvp.kotlin.utils.XLog

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * 网络请求公共代码
 */
open class BaseNetObserver<T>(private val mContext: Context) : Observer<T> {
    private var mOnNextEx: ((T) -> Unit)? = null
    private var mOnErrorEx: ((Throwable) -> Unit)? = null
    private var mOnCompleteEx: (() -> Unit)? = null
    private var mOnSubscribeEx: ((Disposable) -> Unit)? = null

    override fun onSubscribe(d: Disposable) {
        mOnSubscribeEx?.invoke(d)
        onSubscribeEx(d)
    }

    override fun onNext(response: T) {
        mOnNextEx?.invoke(response)
        onNextEx(response)
    }

    override fun onError(e: Throwable) {
        XLog.e(e)
        if (e is HttpException && e.response().errorBody() != null) {
            val errorJson = e.response().errorBody()!!.string()
            val gitHubHttpError = JsonParser.fromJsonObj(errorJson, GitHubHttpError::class.java)
            Toast.makeText(mContext, gitHubHttpError.message, Toast.LENGTH_SHORT).show()
            val newE = Throwable(gitHubHttpError.message)
            mOnErrorEx?.invoke(newE)
            onErrorEx(newE)
            return
        }
        mOnErrorEx?.invoke(e)
        onErrorEx(e)
    }

    override fun onComplete() {
        mOnCompleteEx?.invoke()
        onCompleteEx()
    }

    //以下方法提供匿名内部类的形式调用
    protected open fun onCompleteEx() {

    }

    protected open fun onSubscribeEx(d: Disposable) {

    }

    protected open fun onNextEx(data: T) {

    }

    protected open fun onErrorEx(e: Throwable) {

    }


    //以下方法提供Kotlin Lambda调用
    fun onErrorEx(error: (Throwable) -> Unit) {
        mOnErrorEx = error
    }

    fun onNextEx(next: (T) -> Unit) {
        mOnNextEx = next
    }

    fun onCompleteEx(complete: () -> Unit) {
        mOnCompleteEx = complete
    }

    fun onSubscribeEx(subscribe: (Disposable) -> Unit) {
        mOnSubscribeEx = subscribe
    }
}
