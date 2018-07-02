package com.martin.fast.frame.fastframe.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastframe.dagger.component.DaggerDemoMvpComponent
import com.martin.fast.frame.fastframe.dagger.module.DemoMvpModule
import com.martin.fast.frame.fastframe.mvp.contract.DemoMvpContract
import com.martin.fast.frame.fastframe.mvp.presenter.DemoMvpPresenter
import com.martin.fast.frame.fastlib.base.BaseMvpActivity
import com.martin.fast.frame.fastlib.util.DialogUtil
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction

class DemoMvpActivity :
        BaseMvpActivity<DemoMvpContract.View, DemoMvpPresenter<DemoMvpContract.View>>(),
        DemoMvpContract.View {

    override fun showDialog(msg: String) {
        DialogUtil.showNormalDialog(getActivity(), "title", msg, "commit",
                QMUIDialogAction.ActionListener { dialog, _ -> dialog?.dismiss() })
    }

    override fun layoutRes() = R.layout.activity_demo_mvp

    override fun init(saveInstanceState: Bundle?) {

    }

    override fun daggerInject() {
        DaggerDemoMvpComponent
                .builder()
                .demoMvpModule(DemoMvpModule(this))
                .build()
                .inject(this)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DemoMvpActivity::class.java))
        }
    }

}
