package com.martin.fast.frame.fastframe.mvp.presenter

import com.martin.fast.frame.fastframe.mvp.contract.DemoMvpContract
import com.martin.fast.frame.fastlib.base.BasePresenter
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/29 10:47
 */
class DemoMvpPresenter<T> @Inject constructor(view: DemoMvpContract.View)
    : BasePresenter<DemoMvpContract.View>(view) {

    override fun onCreate() {

    }

}