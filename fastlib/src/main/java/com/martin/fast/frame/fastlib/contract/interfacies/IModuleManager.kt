package com.martin.fast.frame.fastlib.contract.interfacies

import android.content.Context

/**
 * @author ：Martin
 * @date : 2018/6/26 11:18
 */
interface IModuleManager {

    /**
     * 在onCreate之前调用,一般用于解决方法数过多的问题
     */
    fun attachBaseContext(base: Context)

    fun onCreate()

    fun onTerminate()

}