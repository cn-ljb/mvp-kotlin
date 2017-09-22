package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.FollowersContract
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.common.LoginUser
import com.wuba.weizhang.protocol.http.UserProtocol
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/9/22.
 */
class FollowersPresenter(private val mView: FollowersContract.IFollowersView) : FollowersContract.IFollowersPresenter {

    override fun getMvpView() = mView

    private var mPage = 1

    private var mFollowerDisposable: Disposable? = null

    override fun startTask() {
        onRefresh()
    }

    override fun onLoadMore() {
        mPage++
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        mFollowerDisposable = UserProtocol.getFollowersByName(LoginUser.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { getMvpView().showPage(it, page) },
                        { getMvpView().errorPage(it, page) }
                )
    }

    override fun onDestroy() {
        RxUtils.dispose(mFollowerDisposable)
    }


}