package com.ljb.mvp.kotlin.act

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.MainTabAdapter
import com.ljb.mvp.kotlin.contract.HomeContract
import com.ljb.mvp.kotlin.domain.TabBean
import com.ljb.mvp.kotlin.fragment.home.FollowingFragment
import com.ljb.mvp.kotlin.fragment.home.MyFragment
import com.ljb.mvp.kotlin.fragment.home.RepositoriesFragment
import com.ljb.mvp.kotlin.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*
import mvp.ljb.kt.act.BaseMvpFragmentActivity

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class HomeActivity : BaseMvpFragmentActivity<HomeContract.IPresenter>(), HomeContract.IView {

    private var mFirstDownBack: Long = 0L
    private var mCurIndex: Int = 0

    private val mFragments = listOf<Fragment>(
            RepositoriesFragment(),
            FollowingFragment(),
            MyFragment())

    private val mTabList = listOf(
            TabBean(R.drawable.bottom_tab_repos, R.string.repos),
            TabBean(R.drawable.bottom_tab_following, R.string.following),
            TabBean(R.drawable.bottom_tab_my, R.string.my))

    override fun registerPresenter() = HomePresenter::class.java

    override fun getLayoutId() = R.layout.activity_home

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        savedInstanceState?.let {
            supportFragmentManager.popBackStackImmediate(null, 1)
            mCurIndex = it.getInt("index")
        }
    }

    override fun initView() {
        tgv_group.setOnItemClickListener { openTabFragment(it) }
        tgv_group.setAdapter(MainTabAdapter(this, mTabList))
        openTabFragment(mCurIndex)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("index", mCurIndex)
    }

    private fun openTabFragment(position: Int) {
        tgv_group.setSelectedPosition(position)
        val ft = supportFragmentManager.beginTransaction()
        ft.hide(mFragments[mCurIndex])
        var f = supportFragmentManager.findFragmentByTag(mFragments[position].javaClass.simpleName)
        if (f == null) {
            f = mFragments[position]
            ft.add(R.id.fl_content, f, f.javaClass.simpleName)
        }
        ft.show(f).commit()
        mCurIndex = position
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - mFirstDownBack > 2000) {
            Toast.makeText(this, R.string.exit_go_out, Toast.LENGTH_SHORT).show()
            mFirstDownBack = System.currentTimeMillis()
            return
        }
        super.onBackPressed()
    }
}
