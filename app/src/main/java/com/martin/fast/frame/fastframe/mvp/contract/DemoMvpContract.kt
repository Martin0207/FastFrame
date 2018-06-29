package com.martin.fast.frame.fastframe.mvp.contract

import com.martin.fast.frame.fastlib.contract.interfacies.IModel
import com.martin.fast.frame.fastlib.contract.interfacies.IView

/**
 * @author ：Martin
 * @date : 2018/6/29 10:44
 */
interface DemoMvpContract {

    interface View : IView {

        fun showDialog(msg: String)

    }

    interface Model : IModel {
        fun getMsg(): String
    }

}