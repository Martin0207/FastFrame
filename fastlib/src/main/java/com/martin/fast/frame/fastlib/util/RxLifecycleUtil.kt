package com.martin.fast.frame.fastlib.util

import android.app.Activity
import android.support.v4.app.Fragment
import com.martin.fast.frame.fastlib.contract.interfacies.IActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IFragment
import com.martin.fast.frame.fastlib.contract.interfacies.IRxLifecycleAble
import com.martin.fast.frame.fastlib.contract.interfacies.IView
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid

/**
 * @author ：Martin
 * @date : 2018/6/7 20:52
 */
object RxLifecycleUtil {

    /**
     * 绑定RxJava的生命周期至Activity,直到event触发时,结束RxJava的生命
     */
    fun <T> bindUntilEvent(view: IView, event: ActivityEvent): LifecycleTransformer<T> {
        if (view is IActivity) {
            return RxLifecycle.bindUntilEvent((view as IActivity).getBehaviorSubject(), event)
        } else {
            throw NullPointerException("该View层需要继承BaseMvpActivity或实现IActivity接口")
        }
    }

    /**
     * 绑定RxJava的生命周期至Activity,直到event触发时,结束RxJava的生命
     */
    fun <T> bindUntilEvent(view: IView, event: FragmentEvent): LifecycleTransformer<T> {
        if (view is IFragment) {
            return RxLifecycle.bindUntilEvent((view as IFragment).getBehaviorSubject(), event)
        } else {
            throw NullPointerException("该View层需要继承BaseMvpActivity或实现IFragment接口")
        }
    }

    /**
     * 绑定RxJava的生命周期至Activity,直到event触发时,结束RxJava的生命
     */
    fun <T> bindUntilEvent(rxLifecycleAble: IRxLifecycleAble<T>, event: T): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(rxLifecycleAble.getBehaviorSubject(), event)
    }

    /**
     * 绑定RxJava的生命周期至Activity或Fragment
     * case CREATE:
     *   return ActivityEvent.DESTROY;
     *    case START:
     *   return ActivityEvent.STOP;
     *   case RESUME:
     *  return ActivityEvent.PAUSE;
     *    case PAUSE:
     *  return ActivityEvent.STOP;
     *  case STOP:
     *  return ActivityEvent.DESTROY;
     *
     *  如上所示 , 注册所在生命周期不同 , 默认结束生命周期也不同
     */
    fun <T> bindToLifecycle(rxLifecycleAble: IView): LifecycleTransformer<T> {
        if (rxLifecycleAble is IActivity) {
            return RxLifecycleAndroid.bindActivity((rxLifecycleAble as IActivity).getBehaviorSubject())
        } else if (rxLifecycleAble is IFragment) {
            return RxLifecycleAndroid.bindFragment((rxLifecycleAble as IFragment).getBehaviorSubject())
        } else {
            throw NullPointerException("要使用该方法,需要实现IActivity/IFragment或继承BaseActivity/BaseFragment")
        }
    }

    fun <T> bindToLifecycle(activity: Activity): LifecycleTransformer<T> {
        if (activity is IActivity) {
            return RxLifecycleAndroid.bindActivity((activity as IActivity).getBehaviorSubject())
        } else {
            throw NullPointerException("要使用该方法,需要实现IActivity.或继承BaseActivity")
        }
    }

    fun <T> bindToLifecycle(fragment: Fragment): LifecycleTransformer<T> {
        if (fragment is IFragment) {
            return RxLifecycleAndroid.bindFragment((fragment as IFragment).getBehaviorSubject())
        } else {
            throw NullPointerException("要使用该方法,需要实现IFragment.或继承BaseFragment")
        }
    }

}