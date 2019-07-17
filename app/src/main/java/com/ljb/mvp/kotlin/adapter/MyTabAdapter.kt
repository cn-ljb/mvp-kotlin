package com.ljb.mvp.kotlin.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ljb.mvp.kotlin.domain.MyTabFragmentBean

/**
 * Created by L on 2017/7/19.
 */
class MyTabAdapter(fm: FragmentManager, private val mData: Array<MyTabFragmentBean>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = mData[position].fragment

    override fun getCount() = mData.size

    override fun getPageTitle(position: Int) = mData[position].title
}