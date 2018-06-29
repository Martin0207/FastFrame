package com.martin.fast.frame.fastframe.mvp.model

import com.martin.fast.frame.fastframe.mvp.contract.DemoMvpContract
import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/29 10:46
 */
class DemoMvpModel @Inject constructor() : DemoMvpContract.Model {

    override fun getMsg() = "DemoMvpModel 返回的msg"

    override fun onDestroy() {
    }

}