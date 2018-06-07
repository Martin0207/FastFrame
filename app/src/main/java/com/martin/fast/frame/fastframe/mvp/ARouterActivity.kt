package com.martin.fast.frame.fastframe.mvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.martin.fast.frame.fastframe.R

@Route(path = "/main/ARouterActivity")
class ARouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arouter)
    }
}
