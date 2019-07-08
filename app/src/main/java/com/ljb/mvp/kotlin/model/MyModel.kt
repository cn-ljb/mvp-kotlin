package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.dao.IUserDaoProtocol
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import com.ljb.mvp.kotlin.table.UserTable
import dao.ljb.kt.core.DaoFactory
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class MyModel : BaseModel(), MyContract.IModel {

    private val mUserTable = UserTable()

    override fun getUserInfo(): Observable<User> {
        return Observable.concat(
                DaoFactory.getProtocol(IUserDaoProtocol::class.java).queryUserByUserId(mUserTable, LoginUser.uid),
                HttpFactory.getProtocol(IUserHttpProtocol::class.java).getUserInfoByName(LoginUser.login)
        ).filter { User.EMPTY != it }
    }
}