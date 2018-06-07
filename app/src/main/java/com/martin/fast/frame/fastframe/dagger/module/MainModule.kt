package com.martin.fast.frame.fastframe.dagger.module

import com.martin.fast.frame.fastframe.entity.UserEntity
import dagger.Module
import dagger.Provides

/**
 * @author ：Martin
 * @date : 2018/6/7 13:09
 */
@Module
class MainModule {

    @Provides
    fun provide(): UserEntity {
        return UserEntity();
    }

}
