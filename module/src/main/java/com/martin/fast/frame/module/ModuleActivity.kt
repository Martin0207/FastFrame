package com.martin.fast.frame.module

import android.os.Bundle
import com.martin.fast.frame.fastlib.base.BaseActivity

class ModuleActivity : BaseActivity() {
    override fun layoutRes() = R.layout.module_activity_module

    override fun init(saveInstanceState: Bundle?) {
        title = "module activity"

    }
}
