package com.martin.fast.frame.fastlib.contract.interfacies

import android.os.Bundle
import com.trello.rxlifecycle2.android.ActivityEvent

/**
 * @author ：Martin
 * @date : 2018/6/7 19:46
 *
 * 常规Activity的使用 , 一般结构与逻辑十分简单 , Dagger使用场景少,所以不引入
 */
interface IActivity : IRxLifecycleAble<ActivityEvent> {

    /**
     * 是否使用EventBus , 默认不使用
     */
    fun useEventBus(): Boolean = false

    /**
     * 初始化数据
     */
    fun init(saveInstanceState: Bundle?)

    /**
     * 是否含有Fragment,默认false
     * 当为True时,会对Fragment的生命周期进行监听,实现RxJava的内存泄漏安全
     */
    fun haveFragment() = false

}