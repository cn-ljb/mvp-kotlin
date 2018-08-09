package mvp.ljb.kt.presenter

import mvp.ljb.kt.contract.IViewContract

/**
 * Created by L on 2017/7/10.
 */
interface IBasePresenter<out V : IViewContract> {
    fun getMvpView(): V
}




