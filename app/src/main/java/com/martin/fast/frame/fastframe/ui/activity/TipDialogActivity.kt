package com.martin.fast.frame.fastframe.ui.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.util.TipDialogUtil
import kotlinx.android.synthetic.main.activity_tip_dialog.*

class TipDialogActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, TipDialogActivity::class.java))
        }
    }

    override fun layoutRes() = R.layout.activity_tip_dialog

    override fun init(saveInstanceState: Bundle?) {
        setTitle("tip dialog")
        btn_loading.setOnClickListener {
            TipDialogUtil.showLoadingDialog(getActivity(), "loading")
        }

        btn_success.setOnClickListener {
            TipDialogUtil.showSuccessDialog(getActivity())
        }

        btn_error.setOnClickListener {
            TipDialogUtil.showErrorDialog(getActivity())
        }

        btn_tip.setOnClickListener {
            TipDialogUtil.showTipDialog(getActivity())
        }

    }
}
