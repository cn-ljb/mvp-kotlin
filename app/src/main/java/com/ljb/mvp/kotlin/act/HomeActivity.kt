package com.ljb.mvp.kotlin.act

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.domain.TabBean
import com.ljb.mvp.kotlin.fragment.FollowingFragment
import com.ljb.mvp.kotlin.fragment.MyFragment
import com.ljb.mvp.kotlin.fragment.RepositoriesFragment
import com.wuba.weizhang.adapter.MainTabAdapter
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by L on 2017/7/14.
 */
class HomeActivity : FragmentActivity() {

    val mRepositoriesFragment by lazy { RepositoriesFragment() }
    val mFollowingFragment by lazy { FollowingFragment() }
    val mMyFragment by lazy { MyFragment() }


    val mTabList = listOf(
            TabBean(R.drawable.bottom_tab_repos, R.string.repos),
            TabBean(R.drawable.bottom_tab_following, R.string.following),
            TabBean(R.drawable.bottom_tab_my, R.string.my)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
    }

    private fun initView() {
        tgv_group.setOnItemClickListener { openTabFragment(it) }
        tgv_group.setAdapter(MainTabAdapter(this, mTabList))

        //默认首页
        openTabFragment(0)
    }

    private fun openTabFragment(position: Int) {
        tgv_group.setSelectedPosition(position)
        when (position) {
            0 -> mRepositoriesFragment
            1 -> mFollowingFragment
            2 -> mMyFragment
            else -> null
        }?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fl_content, it).commit()
        }
    }

}