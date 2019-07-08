package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.FollowersContract
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class FollowersModel : BaseModel(), FollowersContract.IModel {

    override fun getFollowers(page: Int): Observable<MutableList<Follower>> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getFollowersByName(LoginUser.login, page)
    }
}

