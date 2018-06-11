package com.martin.fast.frame.fastlib.contract.interfacies

import android.app.Dialog

/**
 * @author ：Martin
 * @date : 2018/6/8 17:08
 */
interface IDialogInterface {

    /**
     * checkboxDialog的点击监听
     */
    interface OnCheckboxListener {
        /**
         * 点击监听
         * @param : checkbox是否被选中
         */
        fun onClick(dialog: Dialog, checked: Boolean)
    }

    interface OnMenuListener {
        /**
         * @param position : 选中menu下标
         */
        fun onClick(dialog: Dialog, position: Int)
    }

    interface OnMultiDialogListener {
        fun onClick(dialog: Dialog, checks: IntArray)
    }

}