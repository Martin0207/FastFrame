package com.martin.fast.frame.fastframe.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastframe.ui.fragment.TestFragment
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.ui.adapter.CustomFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_arouter.*

@Route(path = "/main/ARouterActivity")
class ARouterActivity : BaseActivity() {

    val TAG: String = "ARouterActivity"

    override fun init(saveInstanceState: Bundle?) {
        setTitle("ARouter Test")
        vp.adapter = CustomFragmentPagerAdapter(supportFragmentManager, arrayListOf(TestFragment()))
    }

    override fun layoutRes(): Int = R.layout.activity_arouter

    override fun haveFragment(): Boolean = true

}
