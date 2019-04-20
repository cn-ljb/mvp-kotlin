package com.ljb.mvp.kotlin.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import java.util.*
import kotlin.collections.set

/**
 * Author:Ljb
 * Time:2018/12/28
 * There is a lot of misery in life
 **/
object PermissionUtils {

    private val mCallBackMap: WeakHashMap<Int, (permissions: Array<out String>, grantResults: IntArray) -> Unit> = WeakHashMap()

    fun checkPermission(context: Context, permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 必须覆写Activity#onRequestPermissionsResult
     * 并调用  PermissionUtils#onRequestPermissionsResult(int, String[], int[])
     * @param act  Activity
     * @param requestCode 取值范围（0-255）
     */
    fun requestPermission(act: Activity, permissions: Array<out String>, requestCode: Int, callBack: (permissions: Array<out String>, grantResults: IntArray) -> Unit) {
        mCallBackMap[requestCode] = callBack
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val listCallPermission = ArrayList<String>()
            for (permission in permissions) {
                if (!checkPermission(act, permission)) {
                    listCallPermission.add(permission)
                }
            }
            if (listCallPermission.size == 0) {
                doAllPermissionGranted(permissions, requestCode)
            } else {
                ActivityCompat.requestPermissions(act, permissions, requestCode)
            }
        } else {
            doAllPermissionGranted(permissions, requestCode)
        }
    }

    /**
     * 检测所有的权限结果
     * 只要有一个未授权 返回false
     * */
    fun checkAllResult(resultArr: IntArray): Boolean {
        for (result in resultArr) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun doAllPermissionGranted(permissions: Array<out String>, requestCode: Int) {
        val grantResults = IntArray(permissions.size)
        for (i in grantResults.indices) {
            grantResults[i] = PackageManager.PERMISSION_GRANTED
        }
        onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissions.isEmpty() || grantResults.isEmpty()) {
            mCallBackMap.remove(requestCode)
            return
        }
        mCallBackMap[requestCode]?.invoke(permissions, grantResults)
        mCallBackMap.remove(requestCode)
    }
}