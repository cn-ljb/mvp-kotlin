package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.StarredContract
import com.ljb.mvp.kotlin.utils.RxUtils
import com.wuba.weizhang.common.LoginUser
import com.wuba.weizhang.protocol.http.UserProtocol
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/9/21.
 */
class StarredPresenter(private val mView: StarredContract.IStarredView) : StarredContract.IStarredPresenter {

    override fun getMvpView() = mView

    private var mPage = 1

    private var mStarredDisposable: Disposable? = null

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
        mStarredDisposable = UserProtocol.getStarredByName(LoginUser.name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { getMvpView().showPage(it, page) },
                        { getMvpView().errorPage(it, page) }
                )
    }

    override fun onDestroy() {
        RxUtils.dispose(mStarredDisposable)
    }

}