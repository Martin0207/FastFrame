package com.martin.fast.frame.fastlib.entity

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.martin.fast.frame.fastlib.util.image.load.ImageLoader

/**
 * @author ：Martin
 * @date : 2018/6/23 15:55
 */
class ShowImageEntity(
        /**
         * 图片地址path
         */
        val path: String? = null) : Parcelable {

    /**
     * 图片地址uri
     */
    var uri: Uri? = null

    /**
     * 图片名称
     * 预留字段,暂时没用
     */
    var title: String? = null

    /**
     * 图片描述
     * 预留字段,暂时没用
     */
    var description: String? = null

    constructor(parcel: Parcel) : this(parcel.readString()) {
        uri = parcel.readParcelable(Uri::class.java.classLoader)
        title = parcel.readString()
        description = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(path)
        parcel.writeParcelable(uri, flags)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShowImageEntity> {
        override fun createFromParcel(parcel: Parcel): ShowImageEntity {
            return ShowImageEntity(parcel)
        }

        override fun newArray(size: Int): Array<ShowImageEntity?> {
            return arrayOfNulls(size)
        }
    }

}