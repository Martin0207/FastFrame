package com.martin.fast.frame.fastlib.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author ：Martin
 * @date : 2018/6/20 20:54
 */
object KeyboardUtil {

    /**
     * 展示键盘
     * @param activity 提供上下文对象及获取系统服务
     * @param focusView 需要
     */
    fun show(activity: Activity, focusView: View, delay: Int = 100) {
        val inputMgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager

        //判断是否获取了焦点，如果否，则请求焦点
        if (!focusView.isFocused) {
            focusView.isFocusable = true
            focusView.requestFocus()
        }

        inputMgr.showSoftInput(focusView, 0)
    }

    /**
     * 隐藏键盘
     */
    fun hide(activity: Activity, focusView: View) {
        val inputMgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
//        inputMgr.hideSoftInputFromWindow(focusView.windowToken,0)
        inputMgr.hideSoftInputFromInputMethod(focusView.windowToken, 0)
    }

}