package com.martin.fast.frame.fastlib.contract.interfacies

import android.support.design.widget.Snackbar
import android.view.View
import com.martin.fast.frame.fastlib.util.ToastUtil

/**
 * @author ：Martin
 * @date : 2018/6/7 18:33
 * @description : 框架内的MVP需要实现该接口,已做规范
 */
interface IView {

    fun showToast(msg: String) {
        ToastUtil.show(msg)
    }

    fun showSnake(view: View, msg: String) {
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show()
    }

}