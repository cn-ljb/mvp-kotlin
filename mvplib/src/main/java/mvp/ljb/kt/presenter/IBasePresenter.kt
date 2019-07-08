package mvp.ljb.kt.presenter

import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IViewContract

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
interface IBasePresenter<out V : IViewContract, out M : IModelContract> {

    fun registerModel(): Class<out M>

    fun getMvpView(): V

    fun getModel(): M
}




