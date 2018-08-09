package mvp.ljb.kt.view

import mvp.ljb.kt.contract.IPresenterContract

interface IBaseView<out P : IPresenterContract> {
    fun registerPresenter(): Class<out P>
}
