package com.martin.fast.frame.fastlib.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.fast.frame.fastlib.FastLib
import com.martin.fast.frame.fastlib.contract.impl.ActivityLifecycleCallbackImpl
import com.martin.fast.frame.fastlib.contract.impl.LogAdapterImpl
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus

/**
 * @author ：Martin
 * @date : 2018/6/6 20:29
 */
abstract class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        //这里做全局的Activity生命周期监听
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
    }

    override fun onCreate() {
        super.onCreate()
        //日志工具初始化
        Logger.addLogAdapter(LogAdapterImpl())
        //路由器初始化
        ARouter.init(this)
        //快速开发工具初始化
        FastLib.init(this)
    }


}