package mvp.ljb.kt.presenter

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
fun IBasePresenter<*, *>.getContextEx(): Context = when {
    getMvpView() is Activity -> getMvpView() as Activity
    getMvpView() is Fragment -> (getMvpView() as Fragment).activity!!
    else -> throw IllegalStateException("the presenter not found context")
}
