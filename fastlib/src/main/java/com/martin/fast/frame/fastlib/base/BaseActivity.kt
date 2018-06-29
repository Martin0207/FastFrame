package com.martin.fast.frame.fastlib.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IActivity
import com.orhanobut.logger.Logger
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.subjects.BehaviorSubject
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

/**
 * @author ：Martin
 * @date : 2018/6/7 19:50
 */
abstract class BaseActivity : AppCompatActivity(), IActivity {

    private val behaviorSubject = BehaviorSubject.create<ActivityEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        Timber.e("the opening activity is " + this::class.java.simpleName)

        if (useEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        init(savedInstanceState)
    }

    override fun onResume() {
        //设置为竖屏
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onResume()
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