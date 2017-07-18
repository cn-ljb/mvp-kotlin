package com.ljb.mvp.kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.act.LoginActivity
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.presenter.MyPresenter
import com.wuba.weizhang.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_my.*

/**
 * Created by L on 2017/7/18.
 */
class MyFragment : BaseMvpFragment<MyPresenter>(), MyContract.IMyView {

    override fun createPresenter() = MyPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        btn_logout.setOnClickListener {
            mPresenter.logout()
        }
    }

    private fun initData() {

    }

    override fun logoutSuccess() {
        goLogin()
    }

    private fun goLogin() {
        startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }
}