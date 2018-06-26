package com.martin.fast.frame.module

import android.content.Context
import android.util.Log
import com.martin.fast.frame.fastlib.contract.interfacies.IModuleManager

/**
 * @author ：Martin
 * @date : 2018/6/26 14:43
 */
class ModuleManagerImpl : IModuleManager {
    private val tag: String = "ModuleManagerImpl"

    override fun onCreate() {
        Log.e(tag, "module manager on create")
    }

    override fun onTerminate() {
        Log.e(tag, "module manager on terminate")
    }

    override fun attachBaseContext(base: Context) {
        Log.e(tag, "module manager on attachBaseContext")
    }

}