package mvp.ljb.kt.presenter

import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
abstract class BaseMvpPresenter<out V : IViewContract, out M : IModelContract> : IBasePresenter<V, M>, IPresenterContract {

    private var mMVPView: V? = null
    private var mModel: M? = null

    @Suppress("UNCHECKED_CAST")
    override fun register(mvpView: IViewContract) {
        mMVPView = mvpView as V
        mModel = registerModel().newInstance()
    }

    override fun getMvpView() = mMVPView!!

    override fun getModel() = mModel!!

    override fun onCreate() {
        mModel!!.onCreate()
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        if (mMVPView != null) mMVPView = null
        if (mModel != null) {
            mModel!!.onDestroy()
            mModel = null
        }
    }

}


