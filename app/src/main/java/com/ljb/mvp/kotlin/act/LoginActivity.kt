package com.ljb.mvp.kotlin.act

import android.os.Bundle
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.presenter.LoginPresenter
import com.wuba.weizhang.mvp.BaseMvpActivity

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.ILoginView {

    override fun loginSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter() = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
