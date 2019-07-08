package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.dao.IUserDaoProtocol
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import com.ljb.mvp.kotlin.table.UserTable
import dao.ljb.kt.core.DaoFactory
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import mvp.ljb.kt.model.IBaseModel
import net.ljb.kt.client.HttpFactory
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/07
 * @Description input description
 **/
class LoginModel : BaseModel(), LoginContract.IModel {

    private val mUserTable = UserTable()

    override fun getLocLogin(): String? {
        return LoginUser.login
    }

    override fun getUserInfo(userName: String): Observable<User> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getUserInfoByName(userName)
    }

    override fun saveLoginUser2SP(user: User): User {
        LoginUser.login = user.login
        LoginUser.uid = user.id
        LoginUser.name = user.name ?: ""
        LoginUser.img = user.avatar_url
        return user
    }

    override fun saveUser2DB(user: User): Observable<Boolean> {
        return DaoFactory.getProtocol(IUserDaoProtocol::class.java)
                .saveUser(mUserTable, user)
    }

    override fun delayGoHomeTask(): Observable<Long> {
        return Observable.timer(1500, TimeUnit.MILLISECONDS)
    }
}