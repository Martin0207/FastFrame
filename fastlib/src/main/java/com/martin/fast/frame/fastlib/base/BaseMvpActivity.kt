package com.martin.fast.frame.fastlib.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IMvpActivity
import com.martin.fast.frame.fastlib.contract.interfacies.IPresenter
import com.martin.fast.frame.fastlib.contract.interfacies.IView
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/7 18:32
 */
abstract class BaseMvpActivity<P : IPresenter> : BaseActivity(), IMvpActivity {

    @Inject
    @JvmField
    var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerInject()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

}
