package mvp.ljb.kt.presenter

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

fun IBasePresenter<*>.getContextEx(): Context = when {
    getMvpView() is Activity -> getMvpView() as Activity
    getMvpView() is Fragment -> (getMvpView() as Fragment).activity!!
    else -> throw IllegalStateException("the presenter not found context")
}
