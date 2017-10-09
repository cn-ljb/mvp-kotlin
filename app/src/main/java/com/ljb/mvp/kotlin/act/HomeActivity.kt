package com.ljb.mvp.kotlin.act

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.domain.TabBean
import com.ljb.mvp.kotlin.fragment.home.FollowingFragment
import com.ljb.mvp.kotlin.fragment.home.MyFragment
import com.ljb.mvp.kotlin.fragment.home.RepositoriesFragment
import com.wuba.weizhang.adapter.MainTabAdapter
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by L on 2017/7/14.
 */
class HomeActivity : FragmentActivity() {

    private val mFragments = listOf<Fragment>(
            RepositoriesFragment(),
            FollowingFragment(),
            MyFragment())

    private var mCurIndex: Int = 0

    private val mTabList = listOf(
            TabBean(R.drawable.bottom_tab_repos, R.string.repos),
            TabBean(R.drawable.bottom_tab_following, R.string.following),
            TabBean(R.drawable.bottom_tab_my, R.string.my)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState.let { supportFragmentManager.popBackStackImmediate(null, 1) }
        setContentView(R.layout.activity_home)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {
        tgv_group.setOnItemClickListener { openTabFragment(it) }
        tgv_group.setAdapter(MainTabAdapter(this, mTabList))

        if (savedInstanceState == null) {
            //默认首页
            openTabFragment(0)
        } else {
            val index = savedInstanceState.getInt("index")
            openTabFragment(index)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("index", mCurIndex)
    }

    private fun openTabFragment(position: Int) {
        tgv_group.setSelectedPosition(position)
        val ft = supportFragmentManager.beginTransaction()
        mFragments.filterIndexed { index, _ -> index != position }
                .forEach { ft.hide(it) }
        var f = supportFragmentManager.findFragmentByTag(mFragments[position].javaClass.simpleName)
        if (f == null) {
            f = mFragments[position]
            ft.add(R.id.fl_content, f, f.javaClass.simpleName).show(f).commit()
        } else {
            ft.show(f).commit()
        }
        mCurIndex = position
    }

}