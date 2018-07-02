package com.martin.fast.frame.fastlib.util.image.load

import android.content.Context
import android.net.Uri
import android.widget.ImageView

/**
 * @author ：Martin
 * @date : 2018/6/30 20:57
 */
abstract class IImageLoadEngine {

    /**
     * 加载逻辑
     */
    abstract fun into(imgView: ImageView, entity: ImageLoadEntity)

    /**
     * 清除缓存
     */
    abstract fun cleanCache(context: Context)

}