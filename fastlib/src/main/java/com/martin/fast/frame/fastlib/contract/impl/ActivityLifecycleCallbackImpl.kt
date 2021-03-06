package com.martin.fast.frame.fastlib.contract.impl

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.martin.fast.frame.fastlib.R
import com.martin.fast.frame.fastlib.contract.interfacies.IActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IRxLifecycleAble
import com.martin.fast.frame.fastlib.util.AppUtil
import com.qmuiteam.qmui.widget.QMUITopBar
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.subjects.BehaviorSubject

/**
 * @author ：Martin
 * @date : 2018/6/6 22:54
 * @description : 实现Activity的生命周期监听,在这里进行一些全局配置
 */
class ActivityLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.getBehaviorSubject()?.onNext(ActivityEvent.CREATE)
        activity?.let {
            AppUtil.addActivity(activity)
        }
        registerFragmentLifecycleCallback(activity)
    }

    override fun onActivityStarted(activity: Activity?) {
        activity?.getBehaviorSubject()?.onNext(ActivityEvent.START)
        /*
         全局设置TopBar属性
         因为initData在onCreate中执行,则如果设置界面title , 在initData中使用setTitle方法即可
       */
        val qmuiTopBar = activity?.findViewById<QMUITopBar>(R.id.tb)
        qmuiTopBar?.setTitle(activity.title.toString())
        qmuiTopBar?.removeAllLeftViews()
        qmuiTopBar?.addLeftBackImageButton()
        qmuiTopBar?.addLeftBackImageButton()?.setOnClickListener({
            activity.finish()
        })
    }

    override fun onActivityResumed(activity: Activity?) {
        activity?.getBehaviorSubject()?.onNext(ActivityEvent.RESUME)

    }

    override fun onActivityPaused(activity: Activity?) {
        activity?.getBehaviorSubject()?.onNext(ActivityEvent.PAUSE)
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activity?.getBehaviorSubject()?.onNext(ActivityEvent.DESTROY)
        activity?.let {
            AppUtil.removeActivity(activity)
        }
    }

    override fun onActivityStopped(activity: Activity?) {
        activity?.getBehaviorSubject()?.onNext(ActivityEvent.STOP)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    /**
     * 注册activity内的Fragment生命周期监听
     * 如果需要该功能 , 则Activity需要实现IActivity接口,或继承框架内的BaseActivity/BaseMvpActivity
     * 并且,haveFragment方法返回true,默认为false
     */
    private fun registerFragmentLifecycleCallback(activity: Activity?) {
        if (activity is FragmentActivity && activity is IActivity && (activity as IActivity).haveFragment()) {
            (activity as FragmentActivity).supportFragmentManager.registerFragmentLifecycleCallbacks(FragmentLifecycleCallBackImpl(), true)
        }
    }

    /**
     * 根据Activity的实现结构,来获取BehaviorSubject , 以非侵入的方式实现RxJava的管理
     */
    private fun Activity.getBehaviorSubject(): BehaviorSubject<ActivityEvent>? {
        if (this is IRxLifecycleAble<*>) {
            return (this as IRxLifecycleAble<ActivityEvent>).getBehaviorSubject()
        }
        return null
    }

}