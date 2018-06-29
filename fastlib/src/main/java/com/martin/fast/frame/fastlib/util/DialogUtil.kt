package com.martin.fast.frame.fastlib.util

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.text.InputType
import com.martin.fast.frame.fastlib.R
import com.martin.fast.frame.fastlib.contract.interfacies.IDialogInterface
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction

/**
 * @author ：Martin
 * @date : 2018/6/8 15:38
 * @description : 对话框的工具类
 */
object DialogUtil {

    private var mDialog: Dialog? = null

    /**
     *  展示普通的Dialog
     *  仅有  标题,内容,两个按钮
     *  @param activity 启动Dialog的上下文对象 , 需要以Activity作为载体
     *  @param title Dialog的标题
     *  @param affirmMsg 肯定操作的按钮内容
     *  @param listener 肯定操作的点击监听
     *  @param cancelMsg 取消操作的按钮名
     *  @param cancelable Dialog是否可以点击外围取消
     */
    fun showNormalDialog(activity: Activity?, title: String, msg: String, affirmMsg: String,
                         listener: QMUIDialogAction.ActionListener, cancelMsg: String = "取消",
                         cancelable: Boolean = true) {
        dismissDialog()

        val builder = QMUIDialog.MessageDialogBuilder(activity)
                .setTitle(title)
                .setMessage(msg)
        /*
            当设置不可关闭 ,并且强行将cancelMsg设置为空字符串时
            我们认为开发者并不想要取消操作按钮
         */
        if (cancelable && !"".equals(cancelMsg)) {
            builder.addAction(cancelMsg) { dialog, _ ->
                dialog.dismiss()
            }
        }
        builder.addAction(affirmMsg, listener)
        mDialog = builder.create()
        mDialog?.setCancelable(cancelable)

        showDialog(activity)
    }

    /**
     * 单个选择框Dialog
     *
     */
    fun showCheckboxDialog(activity: Activity?, title: String, msg: String, affirmMsg: String, checked: Boolean,
                           listener: IDialogInterface.OnCheckboxListener, cancelMsg: String = "取消",
                           cancelable: Boolean = true) {
        dismissDialog()

        val builder = QMUIDialog.CheckBoxMessageDialogBuilder(activity)
                .setTitle(title)
                .setMessage(msg)
                .addAction(cancelMsg) { dialog, index ->
                    dialog.dismiss()
                }

        builder.addAction(affirmMsg) { dialog, _ ->
            listener.onClick(dialog, builder.isChecked)
        }.isChecked = checked

        mDialog = builder.create()
        mDialog?.setCancelable(cancelable)

        showDialog(activity)
    }

    /**
     * 列表 Dialog
     * 单选,不显示选择框
     */
    fun showMenuDialog(activity: Activity?, items: Array<String>, listener: DialogInterface.OnClickListener) {
        dismissDialog()
        mDialog = QMUIDialog.MenuDialogBuilder(activity)
                .addItems(items, listener)
                .create()
        showDialog(activity)
    }

    /**
     * 列表，单选
     * 显示选择框
     */
    fun showMenuCheckDialog(activity: Activity?, items: Array<String>, checkPosition: Int,
                            listener: DialogInterface.OnClickListener) {
        dismissDialog()
        mDialog = QMUIDialog.CheckableDialogBuilder(activity)
                .addItems(items, listener)
                .setCheckedIndex(checkPosition)
                .create()
        showDialog(activity)
    }

    /**
     * 列表，可多选
     */
    fun showCheckMultiDialog(activity: Activity?, items: Array<String>, checks: IntArray, affirmMsg: String,
                             listener: IDialogInterface.OnMultiDialogListener) {
        dismissDialog()
        val builder = QMUIDialog.MultiCheckableDialogBuilder(activity)
                .addItems(items, null)
                .setCheckedItems(checks)
                .addAction(activity?.getString(R.string.cancel)) { dialog, index -> dialog.dismiss() }
        builder.addAction(affirmMsg) { dialog, _ ->
            listener.onClick(dialog, builder.checkedItemIndexes)
        }
        mDialog = builder.create()

        showDialog(activity)
    }

    /**
     * 输入Dialog
     */
    fun showInputDialog(activity: Activity?, titile: String, hint: String, affimrMsg: String,
                        listener: IDialogInterface.OnInputDialogListener, inputType: Int = InputType.TYPE_CLASS_TEXT) {

        dismissDialog()

        val builder = QMUIDialog.EditTextDialogBuilder(activity)
                .setInputType(inputType)
                .setTitle(titile)
                .setPlaceholder(hint)
                .addAction("取消") { dialog, index ->
                    dialog.dismiss()
                }

        mDialog = builder.addAction(affimrMsg) { dialog, _ ->
            listener.onClick(dialog, builder.editText.text.toString())
        }.create()

        showDialog(activity)
    }

    /**
     * 日期选择Dialog
     */
    fun showDatePickerDialog(activity: Activity?, yearNow: Int, monthNow: Int, dayOfMonthNow: Int,
                             listener: IDialogInterface.OnDatePickListener) {
        dismissDialog()
        mDialog = DatePickerDialog(activity, { view, year, month, dayOfMonth ->
            val date = "$year-$month-$dayOfMonth"
            listener.onDatePick(year, month, dayOfMonth, date)
        }, yearNow, monthNow, dayOfMonthNow)

        showDialog(activity)
    }

    /**
     * 时间选择Dialog
     */
    fun showTimePickerDialog(activity: Activity?, hourOfDayNow: Int, minuteNow: Int,
                             listener: IDialogInterface.OnTimePickListener, is24HourView: Boolean = true) {
        dismissDialog()
        mDialog = TimePickerDialog(activity, { view, hourOfDay, minute ->
            listener.onTimePick(hourOfDay, minute)
        }, hourOfDayNow, minuteNow, is24HourView)

        showDialog(activity)
    }

    /**
     * 展示Dialog
     * 做了Activity的非空与finish判断
     */
    private fun showDialog(activity: Activity?) {
        if (activity == null) {
            return
        }
        if (!activity.isFinishing) {
            mDialog?.show()
        }
    }

    /**
     * 关闭并释放Dialog
     */
    fun dismissDialog() {
        mDialog?.dismiss()
        mDialog = null
    }

}