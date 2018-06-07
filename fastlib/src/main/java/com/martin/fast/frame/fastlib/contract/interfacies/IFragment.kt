package com.martin.fast.frame.fastlib.contract.interfacies

import android.os.Bundle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent

/**
 * @author ：Martin
 * @date : 2018/6/7 19:46
 *
 * 常规Activity的使用 , 一般结构与逻辑十分简单 , Dagger使用场景少,所以不引入
 */
interface IFragment : IRxLifecycleAble<FragmentEvent> {

    /**
     * 是否使用EventBus , 默认不使用
     */
    fun useEventBus(): Boolean = false

    /**
     * 初始化数据
     */
    fun initData(saveInstanceState: Bundle?)


}