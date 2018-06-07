package com.martin.fast.frame.fastlib

import android.content.Context
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @date : 2018/6/6 20:24
 */
object FastLib {

    val DEBUG = true

    var context: Context by Delegates.notNull()
        private set

    fun init(context: Context) {
        this.context = context;
    }

}