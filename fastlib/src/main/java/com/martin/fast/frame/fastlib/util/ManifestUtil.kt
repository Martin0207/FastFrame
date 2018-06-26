package com.martin.fast.frame.fastlib.util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.martin.fast.frame.fastlib.contract.interfacies.IModuleManager
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import timber.log.Timber

/**
 * @author ：Martin
 * @date : 2018/6/26 11:16
 */
object ManifestUtil {

    const val MODULE_MANAGER = "module_manager"

    private val TAG: String = "ManifestUtil"

    /**
     * 获取组件生命周期管理
     * 对于组件化来说,每个组件内可能存在独立的初始化内容
     * 如果将所有的初始化内容都放在App壳模块中 , 会增加一定的耦合度
     * 使用方式:
     * 用户实现[IModuleManager],并在Manifest文件中使用meta标签添加进去
     */
    fun getModuleManagers(context: Context) =
            Observable.just(context)
                    .map {
                        it.packageManager.getApplicationInfo(it.packageName, PackageManager.GET_META_DATA)
                    }
                    .map {
                        it.metaData
                    }
                    .map {
                        val list = ArrayList<IModuleManager>()
                        for (key in it.keySet()) {
                            if (MODULE_MANAGER.equals(it.get(key))) {
                                list.add(ReflectUtil.newInstance(key,null) as IModuleManager)
                            }
                        }
                        list
                    }


}