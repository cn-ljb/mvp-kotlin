package com.ljb.mvp.kotlin.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ljb.mvp.kotlin.domain.MyTabBean

/**
 * Created by L on 2017/7/19.
 */
class MyTabAdapter(fm: FragmentManager?, private val mData: Array<MyTabBean>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = mData[position].fragment

    override fun getCount() = mData.size

    override fun getPageTitle(position: Int) = mData[position].title
}