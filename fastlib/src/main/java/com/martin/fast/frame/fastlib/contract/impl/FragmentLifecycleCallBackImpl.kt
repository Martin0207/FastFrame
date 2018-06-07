package com.martin.fast.frame.fastlib.contract.impl

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.martin.fast.frame.fastlib.contract.interfacies.IRxLifecycleAble
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.subjects.BehaviorSubject

/**
 * @author ：Martin
 * @date : 2018/6/7 22:50
 * @description : 对Fragment进行生命周期监听 , 内部加入RxLifecycle的逻辑, 让框架使用者无需继承框架内的BaseFragment
 * 如果不继承框架内的BaseFragment或BaseMvpFragment,则必须至少实现 IRxLifecycleAble接口
 */
class FragmentLifecycleCallBackImpl : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
        super.onFragmentAttached(fm, f, context)
        f?.getBehaviorSubject()?.onNext(FragmentEvent.ATTACH)
    }

    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        f?.getBehaviorSubject()?.onNext(FragmentEvent.CREATE)

    }

    override fun onFragmentViewCreated(fm: FragmentManager?, f: Fragment?, v: View?, savedInstanceState: Bundle?) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        f?.getBehaviorSubject()?.onNext(FragmentEvent.CREATE_VIEW)

    }

    override fun onFragmentStarted(fm: FragmentManager?, f: Fragment?) {
        super.onFragmentStarted(fm, f)
        f?.getBehaviorSubject()?.onNext(FragmentEvent.START)
    }

    override fun onFragmentResumed(fm: FragmentManager?, f: Fragment?) {
        super.onFragmentResumed(fm, f)
        f?.getBehaviorSubject()?.onNext(FragmentEvent.RESUME)
    }

    override fun onFragmentPaused(fm: FragmentManager?, f: Fragment?) {
        f?.getBehaviorSubject()?.onNext(FragmentEvent.PAUSE)
        super.onFragmentPaused(fm, f)
    }

    override fun onFragmentStopped(fm: FragmentManager?, f: Fragment?) {
        f?.getBehaviorSubject()?.onNext(FragmentEvent.STOP)
        super.onFragmentStopped(fm, f)
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
        f?.getBehaviorSubject()?.onNext(FragmentEvent.DESTROY_VIEW)
        super.onFragmentViewDestroyed(fm, f)
    }

    override fun onFragmentDestroyed(fm: FragmentManager?, f: Fragment?) {
        f?.getBehaviorSubject()?.onNext(FragmentEvent.DESTROY)
        super.onFragmentDestroyed(fm, f)
    }

    override fun onFragmentDetached(fm: FragmentManager?, f: Fragment?) {
        f?.getBehaviorSubject()?.onNext(FragmentEvent.DETACH)
        super.onFragmentDetached(fm, f)
    }

    fun Fragment.getBehaviorSubject(): BehaviorSubject<FragmentEvent>? {
        return if (this is IRxLifecycleAble<*>) (this as IRxLifecycleAble<FragmentEvent>).getBehaviorSubject() else null
    }

}