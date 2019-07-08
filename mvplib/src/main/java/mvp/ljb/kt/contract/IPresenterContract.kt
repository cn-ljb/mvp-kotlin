package mvp.ljb.kt.contract

/**
 * Presenter公共契约接口
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
interface IPresenterContract {

    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun register(mvpView: IViewContract)

}