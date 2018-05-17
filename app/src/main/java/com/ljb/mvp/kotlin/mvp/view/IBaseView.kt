package com.ljb.mvp.kotlin.mvp.view

import com.ljb.mvp.kotlin.mvp.contract.IPresenterContract

interface IBaseView<out P : IPresenterContract> {
    fun createPresenter(): P
}
