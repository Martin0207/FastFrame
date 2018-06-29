package com.martin.fast.frame.fastframe.dagger.component

import com.martin.fast.frame.fastframe.dagger.module.DemoMvpModule
import com.martin.fast.frame.fastframe.ui.activity.DemoMvpActivity
import dagger.Component

/**
 * @author ：Martin
 * @date : 2018/6/29 10:53
 */
@Component(modules = [DemoMvpModule::class])
interface DemoMvpComponent {

    fun inject(activity: DemoMvpActivity)

}