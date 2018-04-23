package com.ljb.mvp.kotlin.presenter.base

import com.ljb.mvp.kotlin.mvp.IBasePresenter
import com.ljb.mvp.kotlin.mvp.IBaseView
import com.ljb.mvp.kotlin.utils.RxUtils
import com.ljb.mvp.kotlin.net.log.XgoLog
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

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
    fun Disposable.bindRxLife(lifeLv: RxLife): Disposable {
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
}