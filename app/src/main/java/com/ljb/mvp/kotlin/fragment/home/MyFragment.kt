package com.ljb.mvp.kotlin.fragment.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.act.LoginActivity
import com.ljb.mvp.kotlin.adapter.MyTabAdapter
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.domain.MyTabBean
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.fragment.EventsFragment
import com.ljb.mvp.kotlin.fragment.FollowersFragment
import com.ljb.mvp.kotlin.fragment.StarredFragment
import com.ljb.mvp.kotlin.img.GlideCircleTransform
import com.ljb.mvp.kotlin.presenter.MyPresenter
import com.ljb.mvp.kotlin.widget.dialog.NormalMsgDialog
import com.wuba.weizhang.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_my.*


/**
 * Created by L on 2017/7/18.
 */
class MyFragment : BaseMvpFragment<MyPresenter>(), MyContract.IMyView {

    private val mTabArr by lazy {
        arrayOf(
                MyTabBean(getString(R.string.events), EventsFragment()),
                MyTabBean(getString(R.string.starred), StarredFragment()),
                MyTabBean(getString(R.string.followers), FollowersFragment())
        )
    }

    private val mLogoutDialog by lazy {
        NormalMsgDialog(activity)
                .setMessage(R.string.logout_user)
                .setLeftButtonInfo(R.string.cancel)
                .setRightButtonInfo(R.string.enter, DialogInterface.OnClickListener { _, _ ->
                    mPresenter.logout()
                })
    }

    override fun createPresenter() = MyPresenter(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_my, null)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        viewpager.offscreenPageLimit = mTabArr.size
        viewpager.adapter = MyTabAdapter(childFragmentManager, mTabArr)
        tablayout.setupWithViewPager(viewpager)
        btn_logout.setOnClickListener { mLogoutDialog.show() }
    }

    private fun initData() {
        mPresenter.startTask()
    }

    override fun logoutSuccess() {
        goLogin()
    }

    private fun goLogin() {
        startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }

    override fun showUserInfo(user: User) {
        Glide.with(this).load(user.avatar_url)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_header)
                .error(R.drawable.default_header)
                .transform(GlideCircleTransform(context))
                .into(iv_header)
        tv_name.text = user.login
        tv_location.text = user.location
        tv_company.text = user.company
    }

}