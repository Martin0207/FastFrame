package com.martin.fast.frame.fastframe.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IDialogInterface
import com.martin.fast.frame.fastlib.util.DialogUtil
import com.martin.fast.frame.fastlib.util.TimeUtil
import com.martin.fast.frame.fastlib.util.ToastUtil
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogUseActivity : BaseActivity() {

    /**
     * 静态代码
     */
    companion object {

        fun start(activity: Activity) {
            val intent = Intent(activity, DialogUseActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun layoutRes(): Int = R.layout.activity_dialog

    override fun init(saveInstanceState: Bundle?) {
        title = "normal dialog"
        val items = arrayOf("aaa", "bbb", "ccc", "ddd")

        btn_normal.setOnClickListener {
            DialogUtil.showNormalDialog(getActivity(), "title", "msg is msg", "commit",
                    QMUIDialogAction.ActionListener { dialog, _ ->
                        ToastUtil.show("你点击了确定")
                        dialog.dismiss()
                    })
        }

        btn_checkbox.setOnClickListener {
            DialogUtil.showCheckboxDialog(getActivity(), "title", "this is checkbox dialog",
                    "commit",
                    false,
                    object : IDialogInterface.OnCheckboxListener {
                        override fun onClick(dialog: Dialog, checked: Boolean) {
                            dialog.dismiss()
                            ToastUtil.show("是否选择了 $checked")
                        }
                    })
        }

        btn_menu.setOnClickListener {
            DialogUtil.showMenuDialog(getActivity(), items,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        ToastUtil.show("your checked is $which")
                    })
        }

        btn_menu_check.setOnClickListener {
            DialogUtil.showMenuCheckDialog(getActivity(), items, 2,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        ToastUtil.show("your checked is $which")
                    })
        }
        btn_multi.setOnClickListener {
            DialogUtil.showCheckMultiDialog(getActivity(), items, arrayOf(1, 2).toIntArray(),
                    "commit",
                    object : IDialogInterface.OnMultiDialogListener {
                        override fun onClick(dialog: Dialog, checks: IntArray) {
                            dialog.dismiss()
                            ToastUtil.show("your checks are ${checks.contentToString()}")
                        }
                    })
        }

        btn_input.setOnClickListener {
            DialogUtil.showInputDialog(getActivity(), "title", "please input your data",
                    "commit",
                    object : IDialogInterface.OnInputDialogListener {
                        override fun onClick(dialog: Dialog, input: String) {
                            dialog.dismiss()
                            ToastUtil.show("your data is $input")
                        }
                    })
        }

        btn_date.setOnClickListener {
            DialogUtil.showDatePickerDialog(getActivity(), TimeUtil.currentYear(),
                    TimeUtil.currentMonth(), TimeUtil.currentDay(),
                    object : IDialogInterface.OnDatePickListener {
                        override fun onDatePick(year: Int, month: Int, day: Int, date: String) {
                            ToastUtil.show("the date you pick is $date")
                        }
                    })
        }

        btn_time.setOnClickListener {
            DialogUtil.showTimePickerDialog(getActivity(), TimeUtil.currentHour(),
                    TimeUtil.currentMinute(),
                    object : IDialogInterface.OnTimePickListener {
                        override fun onTimePick(hour: Int, minute: Int) {
                            ToastUtil.show("the time you pick is $hour:$minute")
                        }
                    })
        }

    }
}
