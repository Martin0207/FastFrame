package com.martin.fast.frame.fastlib.contract.impl

import android.content.Context
import com.martin.fast.frame.fastlib.contract.interfacies.IModuleManager
import com.martin.fast.frame.fastlib.util.ManifestUtil
import io.reactivex.Observable

/**
 * @author ：Martin
 * @date : 2018/6/26 14:51
 */
class ModuleManagerImpl(val context: Context) : IModuleManager {

    val moduleManagers = ArrayList<IModuleManager>()

    init {
        ManifestUtil.getModuleManagers(context)
                .subscribe { list ->
                    moduleManagers.addAll(list)
                }
    }

    override fun attachBaseContext(base: Context) {
        Observable.fromIterable(moduleManagers)
                .subscribe {
                    it.attachBaseContext(base)
                }
    }

    override fun onCreate() {
        Observable.fromIterable(moduleManagers)
                .subscribe {
                    it.onCreate()
                }
    }

    override fun onTerminate() {
        Observable.fromIterable(moduleManagers)
                .subscribe {
                    it.onTerminate()
                }
    }

}