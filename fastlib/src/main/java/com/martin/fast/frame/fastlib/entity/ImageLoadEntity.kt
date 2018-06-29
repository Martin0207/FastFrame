package com.martin.fast.frame.fastlib.entity

import android.net.Uri
import android.widget.ImageView
import java.io.File

/**
 * @author ：Martin
 * @date : 2018/6/27 10:55
 */
class ImageLoadEntity() {

    lateinit var imgView: ImageView

    var placeholderResId: Int = 0
    var errorResId: Int = 0

    val url: String? = null
    val uri: Uri? = null
    val imgFile: File? = null
    val imgId: Int = 0


}