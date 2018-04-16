package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.FollowingContract
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.common.LoginUser
import com.wuba.weizhang.protocol.http.UserProtocol
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/10/9.
 */
class FollowingPresenter(private val mView: FollowingContract.IFollowingView) : FollowingContract.IFollowingPresenter() {

    override fun getMvpView() = mView

    private var mPage = 1

    override fun onLoadMore() {
        mPage++
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        UserProtocol.getFollowingByName(LoginUser.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { getMvpView().showPage(it, page) },
                        { getMvpView().errorPage(it, page) }
                ).bindRxLife(RxLife.ON_DESTROY)
    }

}