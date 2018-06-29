package com.martin.fast.frame.fastlib.contract.interfacies

import com.martin.fast.frame.fastlib.entity.ImageLoadEntity

/**
 * @author ：Martin
 * @date : 2018/6/27 10:53
 */

interface IImageLoaderStrategy {

    fun load(entity: ImageLoadEntity)

    fun cleanMemoryCache()

    fun cleanDiskCache()

}
