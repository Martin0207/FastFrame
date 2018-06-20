package com.martin.fast.frame.fastframe.ui.fragment

import android.app.Dialog
import android.os.Bundle
import com.martin.fast.frame.fastlib.base.BaseFragment
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastlib.contract.interfacies.IDialogInterface
import com.martin.fast.frame.fastlib.util.DialogUtil
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_test.*


/**
 * @author ：Martin
 * @date : 2018/6/8 10:44
 */
class TestFragment : BaseFragment() {
    override fun layoutRes(): Int = R.layout.fragment_test;

    override fun initData(saveInstanceState: Bundle?) {
        tv.setOnClickListener({
            DialogUtil.showCheckboxDialog(
                    activity,
                    "test",
                    "msg",
                    "commit",
                    false,
                    object : IDialogInterface.OnCheckboxListener {
                        override fun onClick(dialog: Dialog, checked: Boolean) {
                            dialog.dismiss()
                            Logger.e("value is $checked")
                        }
                    }
            )
        })
    }

}