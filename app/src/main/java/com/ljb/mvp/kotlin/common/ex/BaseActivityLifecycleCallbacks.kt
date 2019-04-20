package com.ljb.mvp.kotlin.common.ex

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Author:Ljb
 * Time:2019/3/22
 * There is a lot of misery in life
 **/
abstract class BaseActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

    override fun onActivityPaused(activity: Activity?) {
    }

}
