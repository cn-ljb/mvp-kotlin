package mvp.ljb.kt.model

import mvp.ljb.kt.contract.IModelContract

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
abstract class BaseModel : IBaseModel, IModelContract {

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

}
