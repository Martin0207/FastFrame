package com.martin.fast.frame.fastlib.contract.interfacies

import android.content.Context

/**
 * @author ：Martin
 * @date : 2018/6/7 10:33
 * @description : Application的生命周期接口 , 用于管理component的生命周期
 * 一般将Component的特殊配置放在实现类
 */
interface ApplicationLifecycleCallback {

    /**
     *  常用于 MultiDex 以及插件化框架的初始化
     */
    fun attachBaseContext(base: Context?)

    /**
     * 程序开始
     */
    fun onCreate()

    /**
     * 程序终止
     */
    fun onTerminate()

}