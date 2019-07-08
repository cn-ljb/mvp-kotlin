package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.RepositoriesContract
import com.ljb.mvp.kotlin.domain.Repository
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class RepositoriesModel : BaseModel(), RepositoriesContract.IModel {

    override fun getRepositories(page: Int): Observable<MutableList<Repository>> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getRepositoriesByName(LoginUser.login, page)
    }
}