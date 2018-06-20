package com.martin.fast.frame.fastlib.contract.interfacies

import android.app.Dialog

/**
 * @author ：Martin
 * @date : 2018/6/8 17:08
 */
public interface IDialogInterface {

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

    interface OnInputDialogListener {
        fun onClick(dialog: Dialog, input: String)
    }

    interface OnDatePickListener {
        /**
         * 日期选择 返回
         *
         * @param month 月份是 0-11
         * @param date  日期拼接 , 月份已经+1
         */
        fun onDatePick(year: Int, month: Int, day: Int, date: String)
    }

    interface OnTimePickListener {

        fun onTimePick(hour: Int, minute: Int)
    }

}