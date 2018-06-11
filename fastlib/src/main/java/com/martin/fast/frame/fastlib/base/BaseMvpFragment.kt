package com.martin.fast.frame.fastlib.base

import android.os.Bundle
import com.martin.fast.frame.fastlib.contract.interfacies.IMvpFragment
import com.martin.fast.frame.fastlib.contract.interfacies.IPresenter
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/8 11:23
 */
abstract class BaseMvpFragment<P : IPresenter> : BaseFragment(), IMvpFragment {

    @Inject
    @JvmField
    var presenter: P? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        daggerInject()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        presenter?.onDestroy()
        super.onDestroyView()
    }

}