package com.ljb.mvp.kotlin.act

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
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

    override fun loginError(errorMsg: String?) {
        tv_tip.visibility = View.VISIBLE
        if (errorMsg.isNullOrEmpty()) {
            tv_tip.setText(R.string.net_error)
            Toast.makeText(this, R.string.net_error, Toast.LENGTH_SHORT).show()
        } else {
            tv_tip.text = errorMsg
        }
    }

    override fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showLogin() {
        val alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f)
        ObjectAnimator.ofPropertyValuesHolder(ll_login, alpha, scaleX, scaleY).setDuration(1000).start()
        ll_login.visibility = View.VISIBLE
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> login()
        }
    }

    private fun login() {
        if (et_github.text.isNullOrBlank()) {
            tv_tip.visibility = View.VISIBLE
            tv_tip.setText(R.string.input_user)
            return
        }
        mPresenter.login(et_github.text.trim().toString())
    }

}
