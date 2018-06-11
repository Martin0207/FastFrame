package com.martin.fast.frame.fastlib.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.fast.frame.fastlib.contract.interfacies.IFragment
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.subjects.BehaviorSubject
import org.greenrobot.eventbus.EventBus

/**
 * @author ：Martin
 * @date : 2018/6/8 10:44
 */
abstract class BaseFragment : Fragment(), IFragment {

    private val behaviorSubject = BehaviorSubject.create<FragmentEvent>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (useEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        return inflater.inflate(layoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun getBehaviorSubject(): BehaviorSubject<FragmentEvent> = behaviorSubject

    /**
     * 布局Id
     */
    abstract fun layoutRes(): Int

    fun getFragment(): Fragment = this

}