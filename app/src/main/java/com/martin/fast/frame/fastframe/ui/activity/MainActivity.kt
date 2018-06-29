package com.martin.fast.frame.fastframe.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastframe.dagger.component.DaggerMainComponent
import com.martin.fast.frame.fastframe.entity.UserEntity
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.constant.RouterHub
import com.qmuiteam.qmui.widget.QMUITopBar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var user: UserEntity

    override fun layoutRes(): Int = R.layout.activity_main

    override fun init(saveInstanceState: Bundle?) {

        DaggerMainComponent.builder()
                .build()

        btn_router.setOnClickListener {
            ARouter.getInstance()
                    .build(RouterHub.MODULE_ACTIVITY)
                    .navigation()
        }

        btn_dialog.setOnClickListener {
            DialogUseActivity.start(getActivity())
        }

        btn_tip_dialog.setOnClickListener {
            TipDialogActivity.start(getActivity())
        }

        btn_retrofit.setOnClickListener {
            RetrofitActivity.start(getActivity())
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<QMUITopBar>(R.id.tb).removeAllLeftViews()
    }


}
