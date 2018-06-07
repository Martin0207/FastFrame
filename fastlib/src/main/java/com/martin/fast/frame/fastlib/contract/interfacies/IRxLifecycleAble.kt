package com.martin.fast.frame.fastlib.contract.interfacies

import io.reactivex.subjects.BehaviorSubject

/**
 * @author ：Martin
 * @date : 2018/6/7 20:44
 */
interface IRxLifecycleAble<T>{

    fun getBehaviorSubject():BehaviorSubject<T>

}