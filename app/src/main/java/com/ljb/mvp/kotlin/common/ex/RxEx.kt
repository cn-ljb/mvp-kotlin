package com.ljb.mvp.kotlin.common.ex

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * 扩展方法：用于处理订阅事件发生时的公共代码
 * */
fun <T> Observable<T>.subscribeEx(onNext: (data: T) -> Unit = {}, onError: (e: Throwable) -> Unit = {}, onComplete: () -> Unit = {}): Disposable {
    return this.subscribe({
        //编写订阅触发时的公共代码
        onNext.invoke(it)
    }, {
        //编写订阅失败的公共代码
        onError.invoke(it)
    }, {
        //编写订阅完成后的公共代码
        onComplete.invoke()
    })
}