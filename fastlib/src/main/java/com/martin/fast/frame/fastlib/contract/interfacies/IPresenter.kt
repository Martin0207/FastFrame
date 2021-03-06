package com.martin.fast.frame.fastlib.contract.interfacies

/**
 * @author ：Martin
 * @date : 2018/6/7 18:32
 */
interface IPresenter {

    /**
     * 初始化资源
     */
    fun onCreate()

    /**
     * 释放资源
     */
    fun onDestroy()

}