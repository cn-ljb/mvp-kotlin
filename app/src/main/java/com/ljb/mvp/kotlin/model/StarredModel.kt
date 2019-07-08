package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.StarredContract
import com.ljb.mvp.kotlin.domain.Starred
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class StarredModel : BaseModel(), StarredContract.IModel {

    override fun getStarred(page: Int): Observable<MutableList<Starred>> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getStarredByName(LoginUser.login, page)
    }
}