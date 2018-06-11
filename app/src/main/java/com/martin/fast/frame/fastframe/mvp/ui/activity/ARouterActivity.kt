package com.martin.fast.frame.fastframe.mvp.ui.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastframe.mvp.ui.fragment.TestFragment
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.ui.adapter.CustomFragmentPagerAdapter
import com.martin.fast.frame.fastlib.util.RxLifecycleUtil
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_arouter.*
import java.util.concurrent.TimeUnit

@Route(path = "/main/ARouterActivity")
class ARouterActivity : BaseActivity() {

    val TAG: String = "ARouterActivity"

    override fun initData(saveInstanceState: Bundle?) {
        setTitle("ARouter Test")
        vp.adapter = CustomFragmentPagerAdapter(supportFragmentManager, arrayListOf(TestFragment()))
    }


    override fun layoutRes(): Int = R.layout.activity_arouter

    override fun haveFragment(): Boolean = true

}
