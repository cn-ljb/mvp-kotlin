package mvp.ljb.kt.view

import mvp.ljb.kt.contract.IPresenterContract

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
interface IBaseView<out P : IPresenterContract> {
    fun registerPresenter(): Class<out P>
}
