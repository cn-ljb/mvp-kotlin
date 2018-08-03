package com.ljb.mvp.view

import com.ljb.mvp.contract.IPresenterContract

interface IBaseView<out P : IPresenterContract> {
    fun registerPresenter(): Class<out P>
}
