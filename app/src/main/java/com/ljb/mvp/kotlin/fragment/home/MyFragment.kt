package com.ljb.mvp.kotlin.fragment.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.act.LoginActivity
import com.ljb.mvp.kotlin.adapter.MyTabAdapter
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.domain.MyTabFragmentBean
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.fragment.EventsFragment
import com.ljb.mvp.kotlin.fragment.FollowersFragment
import com.ljb.mvp.kotlin.fragment.StarredFragment
import com.ljb.mvp.kotlin.img.ImageLoader
import com.ljb.mvp.kotlin.mvp.BaseMvpFragment
import com.ljb.mvp.kotlin.presenter.MyPresenter
import com.ljb.mvp.kotlin.widget.dialog.NormalMsgDialog
import kotlinx.android.synthetic.main.fragment_my.*


/**
 * Created by L on 2017/7/18.
 */
class MyFragment : BaseMvpFragment<MyPresenter>(),
        MyContract.IMyView {

    private val mTabArr by lazy {
        arrayOf(
                MyTabFragmentBean(getString(R.string.events), EventsFragment()),
                MyTabFragmentBean(getString(R.string.starred), StarredFragment()),
                MyTabFragmentBean(getString(R.string.followers), FollowersFragment())
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
            inflater.inflate(R.layout.fragment_my, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    private fun initView() {
        viewpager.apply {
            offscreenPageLimit = mTabArr.size
            adapter = MyTabAdapter(childFragmentManager, mTabArr)
            tablayout.setupWithViewPager(this)
        }
        btn_logout.setOnClickListener { mLogoutDialog.show() }
    }

    private fun initData() {
        mPresenter.getUserInfo()
    }

    override fun logoutSuccess() {
        goLogin()
    }

    private fun goLogin() {
        startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }

    override fun showUserInfo(user: User) {
        ImageLoader.load(context = activity,
                url = user.avatar_url,
                loadingImgResId = R.drawable.default_header,
                loadErrorImgResId = R.drawable.default_header,
                form = ImageLoader.ImageForm.CIRCLE,
                img = iv_header)
        tv_name.text = user.login
        tv_location.text = user.location
        tv_company.text = user.company
    }

}