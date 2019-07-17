package com.ljb.mvp.kotlin.common.rx

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import cn.nekocode.rxlifecycle.LifecycleEvent
import cn.nekocode.rxlifecycle.RxLifecycle
import cn.nekocode.rxlifecycle.compact.RxLifecycleCompact
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Author:Ljb
 * Time:2018/12/28
 * There is a lot of misery in life
 **/
object RxUtils {

    /**
     * rx 取消订阅
     * */
    fun dispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    /**
     * rx 生命周期管理
     * */
    fun <T> bindToLifecycle(obj: Any): ObservableTransformer<T, T> {
        return if (obj is AppCompatActivity) {
            RxLifecycleCompact.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else if (obj is FragmentActivity) {
            RxLifecycle.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else if (obj is Activity) {
            RxLifecycle.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else if (obj is Fragment) {
            RxLifecycleCompact.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else {
            throw IllegalArgumentException("obj isn't activity or fragment")
        }
    }

    /**
     * rx 线程调度
     *  io -> android.main
     * */
    fun <T> schedulerIO2Main(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

}