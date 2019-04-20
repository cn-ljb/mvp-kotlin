package com.ljb.mvp.kotlin.common.rx

import android.content.Context
import io.reactivex.Observable

/**
 * Author:Ljb
 * Time:2019/3/25
 * There is a lot of misery in life
 **/
fun <T> Observable<T>.subscribeEx(func: (BaseObserver<T>.() -> Unit)) {
    subscribe(BaseObserver<T>().apply(func))
}

fun <T> Observable<T>.subscribeNet(context: Context, func: (BaseNetObserver<T>.() -> Unit)) {
    subscribe(BaseNetObserver<T>(context).apply(func))
}

