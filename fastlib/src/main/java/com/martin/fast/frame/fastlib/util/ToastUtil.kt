package com.martin.fast.frame.fastlib.util

import android.widget.Toast
import com.martin.fast.frame.fastlib.FastLib
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

/**
 * @author ：Martin
 * @date : 2018/6/7 18:36
 */
object ToastUtil {

    private val toast: Toast = Toast.makeText(FastLib.context.applicationContext, "", Toast.LENGTH_SHORT)

    /**
     * 全局Toast , 优化Toast循环弹出问题
     * 默认显示时间为Toast.LENGTH_SHORT
     */
    fun show(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        toast.duration =duration
        Observable.just(msg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toast.apply {
                        this.setText(it)
                    }.show()
                }
    }

    fun show(msgId: Int, duration: Int = Toast.LENGTH_SHORT) {
        toast.duration = duration
        Observable.just(FastLib.context.resources.getString(msgId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toast.apply {
                        this.setText(it)
                    }.show()
                }
    }

}