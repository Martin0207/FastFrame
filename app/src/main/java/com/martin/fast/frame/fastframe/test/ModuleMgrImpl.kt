package com.martin.fast.frame.fastframe.test

import android.content.Context
import android.util.Log
import com.martin.fast.frame.fastlib.contract.interfacies.IModuleManager

/**
 * @author ：Martin
 * @date : 2018/6/26 15:14
 */
class ModuleMgrImpl : IModuleManager{

    private val TAG: String = "ModuleMgrImpl"

    override fun attachBaseContext(base: Context) {
        Log.e(TAG,"attachBaseContext")
    }

    override fun onCreate() {
        Log.e(TAG,"onCreate")
    }

    override fun onTerminate() {
        Log.e(TAG,"onTerminate")
    }

}