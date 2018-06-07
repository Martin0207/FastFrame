package com.martin.fast.frame.fastlib.base

import android.os.Bundle
import com.martin.fast.frame.fastlib.contract.interfacies.IActivity
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.subjects.BehaviorSubject
import org.greenrobot.eventbus.EventBus

/**
 * @author ：Martin
 * @date : 2018/6/7 19:50
 */
abstract class BaseActivity : RxAppCompatActivity(), IActivity {

    private val behaviorSubject = BehaviorSubject.create<ActivityEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        if (useEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        initData(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun getBehaviorSubject(): BehaviorSubject<ActivityEvent> {
        return behaviorSubject
    }

    /**
     * 布局Id
     */
    abstract fun layoutRes(): Int

    fun getContext() = this

    fun getActivity() = this

}