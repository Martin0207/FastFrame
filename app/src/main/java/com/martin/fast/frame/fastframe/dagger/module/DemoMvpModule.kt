package com.martin.fast.frame.fastframe.dagger.module

import com.martin.fast.frame.fastframe.mvp.contract.DemoMvpContract
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/29 10:51
 */
@Module
class DemoMvpModule constructor(var view: DemoMvpContract.View) {

    @Provides
    fun providesView() = view

}