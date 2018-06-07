package com.martin.fast.frame.fastlib.contract.impl

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.martin.fast.frame.fastlib.util.AppUtil

/**
 * @author ：Martin
 * @date : 2018/6/6 22:54
 * @description :
 */
class ActivityLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activity?.let {
            AppUtil.removeActivity(activity)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            AppUtil.addActivity(activity)
        }
    }

}