package com.martin.fast.frame.fastlib.base

import android.view.View
import com.martin.fast.frame.fastlib.contract.interfacies.IModel
import com.martin.fast.frame.fastlib.contract.interfacies.IPresenter
import com.martin.fast.frame.fastlib.contract.interfacies.IView
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/7 20:28
 */
abstract class BasePresenter<V : IView>
constructor(var view: V?)
    : IPresenter {

    init {
        onCreate()
    }

    override fun onDestroy() {
        view = null
    }

}