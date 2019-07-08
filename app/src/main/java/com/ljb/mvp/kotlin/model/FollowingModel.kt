package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.FollowingContract
import com.ljb.mvp.kotlin.domain.Following
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class FollowingModel : BaseModel(), FollowingContract.IModel {

    override fun getFollowing(page: Int): Observable<MutableList<Following>> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getFollowingByName(LoginUser.login, page)
    }
}