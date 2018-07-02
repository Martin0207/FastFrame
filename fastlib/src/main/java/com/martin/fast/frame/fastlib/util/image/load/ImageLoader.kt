package com.martin.fast.frame.fastlib.util.image.load

import android.annotation.SuppressLint
import android.content.Context
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @date : 2018/6/27 10:52
 */
object ImageLoader {

    var imageLoader: IImageLoadEngine by Delegates.notNull()

    fun init(loader: IImageLoadEngine) {
        this.imageLoader = loader
    }

    fun with(context: Context) = ImageLoadEntity(context)

}