package com.martin.fast.frame.fastframe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.fast.frame.fastframe.dagger.component.DaggerMainComponent
import com.martin.fast.frame.fastframe.entity.UserEntity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var user: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
                .build()
                .inject(this)

        btn_router.setOnClickListener({
            Logger.e(user.toString())
        })
    }
}
