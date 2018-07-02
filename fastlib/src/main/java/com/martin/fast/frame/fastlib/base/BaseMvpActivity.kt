package com.martin.fast.frame.fastlib.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IMvpActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IPresenter
import com.martin.fast.frame.fastlib.contract.interfacies.IView
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.subjects.BehaviorSubject
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import java.io.File
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/7 18:32
 */
abstract class BaseMvpActivity<V : IView, P : BasePresenter<V>> : AppCompatActivity(), IMvpActivity {

    private val behaviorSubject = BehaviorSubject.create<ActivityEvent>()

    @Inject
    @JvmField
    protected var presenter: P? = null

    @SuppressLint("BinaryOperationInTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        daggerInject()
        Timber.e("the opening activity is " + this::class.java.simpleName)

        if (useEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        presenter?.onCreate()
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
        presenter?.onDestroy()
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
