package com.ljb.mvp.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.FollowersAdapter
import kotlinx.android.synthetic.main.fragment_events.*

/**
 * Created by L on 2017/7/19.
 */
class FollowersFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_followers, null)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        val manager = GridLayoutManager(activity, 3)
        recyclerview.layoutManager = manager
        recyclerview.adapter = FollowersAdapter(activity)
    }
}