package com.ljb.mvp.kotlin.presenter.base

import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.presenter.IBasePresenter
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.util.*

abstract class BaseRxLifePresenter<out V : IViewContract> : IBasePresenter<V>, IPresenterContract {

    private var mMVPView: V? = null

    @Suppress("UNCHECKED_CAST")
    override fun registerMvpView(mvpView: IViewContract) {
        mMVPView = mvpView as V
    }

    override fun getMvpView() = mMVPView!!

    enum class RxLife {
        ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY
    }

    private val mRxLifeMap = WeakHashMap<RxLife, ArrayList<WeakReference<Disposable>>>()

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
        mMVPView = null
    }

    private fun destroyRxLife(rxLife: RxLife) {
        mRxLifeMap[rxLife]?.map {
            rxDispose(it.get())
        }
        mRxLifeMap[rxLife]?.clear()
    }

    private fun rxDispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    /**
     * 扩展方法：用于管理RxJava生命周期
     * */
    fun Disposable.bindRxLifeEx(lifeLv: RxLife): Disposable {
        if (mRxLifeMap[lifeLv] != null) {
            mRxLifeMap[lifeLv]!!.add(WeakReference(this))
        } else {
            val rxList = ArrayList<WeakReference<Disposable>>()
            rxList.add(WeakReference(this))
            mRxLifeMap[lifeLv] = rxList
        }
        return this
    }

}


