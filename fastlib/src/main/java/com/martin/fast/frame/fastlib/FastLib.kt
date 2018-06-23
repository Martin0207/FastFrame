package com.martin.fast.frame.fastlib

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.fast.frame.fastlib.contract.impl.LogAdapterImpl
import com.orhanobut.logger.Logger
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @date : 2018/6/6 20:24
 */
object FastLib {

    /**
     * 是否为Debug
     */
    var DEBUG = true


    /**
     * 上下文对象
     */
    var context: Context by Delegates.notNull()
        private set

    /**
     * 初始化
     * 需要在Application中调用
     */
    fun init(application: Application) {
        this.context = application
    }

}