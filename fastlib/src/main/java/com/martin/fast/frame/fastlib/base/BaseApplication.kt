package com.martin.fast.frame.fastlib.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.StrictMode
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.fast.frame.fastlib.FastLib
import com.martin.fast.frame.fastlib.contract.impl.ActivityLifecycleCallbackImpl
import com.martin.fast.frame.fastlib.contract.impl.LogAdapterImpl
import com.martin.fast.frame.fastlib.util.ToastUtil
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

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

        //快速开发工具初始化
        FastLib.init(this)

        initLog()
        initARouter(this)

        /*
          解决 7.0 uri 暴露异常
         */
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()
    }

    /**
     * 初始化日志工具
     */
    private fun initLog() {
        //日志工具初始化
        Logger.addLogAdapter(LogAdapterImpl())

        Timber.plant(Timber.DebugTree())
    }

    /**
     * 初始化ARouter
     */
    private fun initARouter(application: Application) {
        // 必须写在init之前，否则这些配置在init过程中将无效
        if (FastLib.DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        //路由器初始化
        ARouter.init(application)
    }
}