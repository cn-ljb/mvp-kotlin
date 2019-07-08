package mvp.ljb.kt.contract

/**
 * View层公共契约接口
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
interface IViewContract {

    fun showToast(resId: Int)

    fun showToast(text: String?)

}