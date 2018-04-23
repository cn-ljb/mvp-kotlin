package com.ljb.mvp.kotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object NetUtils {

    fun checkHasNet(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        if (manager != null && manager is ConnectivityManager) {
            val info = manager.activeNetworkInfo
            if (info != null && info.isConnected) return true
        }
        return false
    }


}