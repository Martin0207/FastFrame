package com.martin.fast.frame.fastlib.util

import com.martin.fast.frame.fastlib.contract.interfacies.IImageLoaderStrategy
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @date : 2018/6/27 10:52
 */
class ImageLoader private constructor() {

    /**
     * 图片加载引擎
     */
    var imageLoader: IImageLoaderStrategy? = null

    companion object {
        var instance: ImageLoader = ImageLoader()
    }


}