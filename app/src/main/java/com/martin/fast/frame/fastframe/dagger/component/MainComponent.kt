package com.martin.fast.frame.fastframe.dagger.component

import com.martin.fast.frame.fastframe.MainActivity

import dagger.Component

/**
 * @author ：Martin
 * @date : 2018/6/7 13:45
 */
@Component
interface MainComponent {

    fun inject(activity: MainActivity)

}
