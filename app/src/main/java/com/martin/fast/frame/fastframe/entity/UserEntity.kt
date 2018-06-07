package com.martin.fast.frame.fastframe.entity

import javax.inject.Inject

/**
 * @author ：Martin
 * @date : 2018/6/7 12:43
 */
class UserEntity {

    var name: String? = "name is martin"

    @Inject
    constructor()

    override fun toString(): String {
        return "UserEntity(name=$name)"
    }


}