package com.ljb.mvp.kotlin.act

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.contract.LoginContract
import com.ljb.mvp.kotlin.presenter.LoginPresenter
import com.wuba.weizhang.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.ILoginView, View.OnClickListener {

    override fun createPresenter() = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initData()
    }

    private fun initView() {
        btn_login.setOnClickListener(this)
    }

    private fun initData() {
        mPresenter.startTask()
    }

    override fun loginSuccess() {
        goHome()
    }

    override fun loginError(msg: String) {

    }

    override fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun showLogin() {
        ll_login.visibility = View.VISIBLE
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> login()
        }
    }

    private fun login() {
        if (et_github.text.isNullOrBlank()) {
            Toast.makeText(this, R.string.input_user, Toast.LENGTH_SHORT).show()
            return
        }
        mPresenter.login(et_github.text.trim().toString())
    }

}
