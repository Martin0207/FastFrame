package com.martin.fast.frame.fastlib.contract.impl

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig

/**
 * @author ：Martin
 * @date : 2018/6/7 9:22
 */
class LogAdapterImpl : AndroidLogAdapter() {

    override fun isLoggable(priority: Int, tag: String?): Boolean {
        tag?.equals("release").let {
            return true
        }
        return true
    }

}