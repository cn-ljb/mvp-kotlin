package com.ljb.mvp.kotlin.presenter.base

import android.widget.Toast
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.mvp.getContext
import com.ljb.mvp.kotlin.utils.RxUtils
import com.ljb.mvp.kotlin.net.log.XgoLog
import com.ljb.mvp.kotlin.utils.NetUtils
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.net.SocketTimeoutException

abstract class BaseRxLifePresenter<out V : IBaseView>(private val mMVPView: V) : IBasePresenter<V> {

    private val mRxLifeMap = HashMap<RxLife, ArrayList<Disposable>>()

    override fun getMvpView() = mMVPView

    override fun onCreate() {
        destroyRxLife(RxLife.ON_CREATE)
    }

    override fun onStart() {
        destroyRxLife(RxLife.ON_START)
    }

    override fun onResume() {
        destroyRxLife(RxLife.ON_RESUME)
    }

    override fun onPause() {
        destroyRxLife(RxLife.ON_PAUSE)
    }

    override fun onStop() {
        destroyRxLife(RxLife.ON_STOP)
    }

    override fun onDestroy() {
        destroyRxLife(RxLife.ON_DESTROY)
    }

    enum class RxLife {
        ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY
    }

    private fun destroyRxLife(rxLife: RxLife) {
        mRxLifeMap[rxLife]?.map {
            XgoLog.i("=== BaseRxLifePresenter del $rxLife : ${it.hashCode()} ===")
            RxUtils.dispose(it)
        }
        mRxLifeMap[rxLife]?.clear()
    }

    /**
     * 扩展方法：用于管理RxJava生命周期
     * */
    fun Disposable.bindRxLifeEx(lifeLv: RxLife): Disposable {
        XgoLog.i("=== BaseRxLifePresenter add $lifeLv : ${this.hashCode()} ===")
        if (mRxLifeMap[lifeLv] != null) {
            mRxLifeMap[lifeLv]!!.add(this)
        } else {
            val rxList = ArrayList<Disposable>()
            rxList.add(this)
            mRxLifeMap[lifeLv] = rxList
        }
        return this
    }

    /**
     * 扩展方法：用于处理订阅事件发生时的公共代码
     * */
    fun <T> Observable<T>.subscribeEx(onNext: (data: T) -> Unit = {}, onError: (e: Throwable) -> Unit = {}, onComplete: () -> Unit = {}): Disposable {
        return this.subscribe({
            //编写订阅触发时的公共代码
            onNext.invoke(it)
        }, {
            //编写订阅失败的公共代码
            if (!NetUtils.checkHasNet(getContext())) {
                Toast.makeText(getContext(), R.string.net_error, Toast.LENGTH_SHORT).show()
            } else if (it is SocketTimeoutException) {
                Toast.makeText(getContext(), R.string.net_time_out, Toast.LENGTH_SHORT).show()
            }
            onError.invoke(it)
        }, {
            //编写订阅完成后的公共代码
            onComplete.invoke()
        })
    }
}