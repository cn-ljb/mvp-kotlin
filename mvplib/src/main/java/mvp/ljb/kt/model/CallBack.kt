package mvp.ljb.kt.model

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
abstract class CallBack<T> {

    open fun onNext(data: T) {
    }

    open fun onError(t: Throwable) {
    }

}
