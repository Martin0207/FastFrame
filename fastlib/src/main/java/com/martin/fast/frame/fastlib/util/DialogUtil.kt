package com.martin.fast.frame.fastlib.util

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import com.martin.fast.frame.fastlib.contract.interfacies.IDialogInterface
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction

/**
 * @author ：Martin
 * @date : 2018/6/8 15:38
 * @description : 对话框的工具类
 */
object DialogUtil {

    var mDialog: Dialog? = null

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
    fun normalDialog(activity: Activity?, title: String, msg: String, affirmMsg: String,
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
            builder.addAction(cancelMsg, { dialog, index ->
                dialog.dismiss()
            })
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
    fun checkboxDialog(activity: Activity?, title: String, msg: String, affirmMsg: String, checked: Boolean,
                       listener: IDialogInterface.OnCheckboxListener, cancelMsg: String = "取消",
                       cancelable: Boolean = true) {
        dismissDialog()

        val builder = QMUIDialog.CheckBoxMessageDialogBuilder(activity)
                .setTitle(title)
                .setMessage(msg)
                .addAction(cancelMsg, { dialog, index ->
                    dialog.dismiss()
                })

        builder.addAction(affirmMsg, { dialog, index ->
            listener.onClick(dialog, builder.isChecked)
        }).setChecked(checked)

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
                .addAction("取消", { dialog, index -> dialog.dismiss() })
        builder.setLeftAction(affirmMsg, { dialog, index ->
            listener.onClick(dialog, builder.checkedItemIndexes)
        })
        mDialog = builder.create()

        showDialog(activity)
    }


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