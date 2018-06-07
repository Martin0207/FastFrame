package com.martin.fast.frame.fastlib.util

import android.app.Activity

/**
 * @author ：Martin
 * @date : 2018/6/6 22:36
 */
object AppUtil {

    /**
     * 管理整个项目的Activity
     */
    val activityList: ArrayList<Activity> = ArrayList()

    /**
     * 将开启的Activity添加到管理
     */
    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

    /**
     * 当Activity关闭时,移除管理集合内的对象
     */
    fun removeActivity(activity: Activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity)
        }
    }


    /**
     * 退出程序
     */
    fun exitApp() {
        finishAllActivity()
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    /**
     * 关闭所有
     */
    private fun finishAllActivity() {
        synchronized(AppUtil::class) {
            val iterator = activityList.iterator()
            while (iterator.hasNext()) {
                val activity = iterator.next()
                activity.finish()
            }
            activityList.clear()
        }
    }

}