package com.martin.fast.frame.fastlib.util.image.load

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.DrawableRequestBuilder
import com.bumptech.glide.Glide

/**
 * @author ：Martin
 * @date : 2018/6/30 21:21
 */
class GlideEngine : IImageLoadEngine() {

    override fun into(imgView: ImageView, entity: ImageLoadEntity) {
        val with = Glide.with(entity.context)

        val load: DrawableRequestBuilder<*>
        if (!TextUtils.isEmpty(entity.path)) {
            load = with.load(entity.path)
        } else if (entity.uri != null) {
            load = with.load(entity.uri)
        } else if (entity.resId > 0) {
            load = with.load(entity.resId)
        } else {
            throw NullPointerException("请load图片资源")
        }
        if (entity.circle) {
            load.centerCrop().bitmapTransform(GlideCircleTransform(entity.context))
        } else if (entity.radius > 0) {
            load.centerCrop().bitmapTransform(GlideRadiusTransform(entity.context, entity.radius))
        }
        load.into(imgView)
    }

    override fun cleanCache(context: Context) {
        Glide.get(context).clearDiskCache()
        Glide.get(context).clearMemory()
    }
}