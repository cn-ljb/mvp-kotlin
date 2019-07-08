package  com.ljb.mvp.kotlin.model

import com.ljb.mvp.kotlin.common.LoginUser
import com.ljb.mvp.kotlin.contract.EventsContract
import com.ljb.mvp.kotlin.domain.Event
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class EventsModel : BaseModel(), EventsContract.IModel {

    override fun getEvents(page: Int): Observable<MutableList<Event>> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
                .getEventsByName(LoginUser.login, page)
    }
}