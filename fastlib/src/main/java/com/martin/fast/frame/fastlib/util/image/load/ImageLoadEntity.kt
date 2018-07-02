package com.martin.fast.frame.fastlib.util.image.load

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @date : 2018/6/30 23:21
 */
class ImageLoadEntity(val context: Context) {

    var path: String? = null
    var uri: Uri? = null
    var resId: Int = 0

    /**
     * 是否加载圆形图
     */
    var circle: Boolean = false

    /**
     * 圆角,若设置圆角,则不可加载圆形图
     */
    var radius: Float = 0F;

    fun circle(): ImageLoadEntity {
        radius = 0F
        this.circle = true
        return this
    }

    fun radius(radius: Float): ImageLoadEntity {
        if (radius > 0) {
            this.radius = radius
            circle = false
        }
        return this
    }

    fun load(path: String): ImageLoadEntity {
        this.path = path
        return this
    }

    fun load(uri: Uri): ImageLoadEntity {
        this.uri = uri
        return this
    }

    fun load(resId: Int): ImageLoadEntity {
        this.resId = resId
        return this
    }

    fun into(imgView: ImageView) {
        ImageLoader.imageLoader.into(imgView, this)
    }

}