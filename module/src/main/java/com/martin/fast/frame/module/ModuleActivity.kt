package com.martin.fast.frame.module

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.constant.RouterHub

@Route(path = RouterHub.MODULE_ACTIVITY)
class ModuleActivity : BaseActivity() {
    override fun layoutRes() = R.layout.module_activity_module

    override fun init(saveInstanceState: Bundle?) {
        title = "module activity"

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}
