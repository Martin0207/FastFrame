package com.martin.fast.frame.fastlib.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import com.martin.fast.frame.fastlib.FastLib

import com.martin.fast.frame.fastlib.R
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.internal.utils.MediaStoreCompat

import io.reactivex.Observable

/**
 * 作者：Martin on 2018/1/30 15:53
 * 邮箱：martin0207mfh@163.com
 */
object PhotoUtil {

    private val authority = "com.martin.fast.frame.fastlib.file_provider"

    /**
     * 选择照片,可拍照
     *
     * @param requestCode 请求码
     * @param pickCount   最大选择数量
     * @param takePhoto   是否进行拍照
     * 返回的内容，可通过 Matisse.obtainResult(data) 来获取图片地址的集合
     */
    @SuppressLint("CheckResult")
    fun pickPhoto(activity: Activity, requestCode: Int, pickCount: Int, takePhoto: Boolean) {
        RxPermissions(activity)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { aBoolean ->
                    if (aBoolean!!) {
                        Matisse.from(activity)
                                .choose(MimeType.ofAll())
                                .countable(true)
                                .capture(takePhoto)
                                .captureStrategy(CaptureStrategy(true, authority))
                                .maxSelectable(pickCount)
                                //                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(activity.resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine(GlideEngine())
                                .forResult(requestCode)
                    }
                }
    }

    /**
     * 只拍照
     *
     * @param activity    上下文对象
     * @param requestCode 请求码
     * @return 可以通过 “mediaStoreCompat.getCurrentPhotoPath()” 来获取图片的Uri
     */
    fun takePhoto(activity: Activity, requestCode: Int): MediaStoreCompat {
        val mediaStoreCompat = MediaStoreCompat(activity)
        mediaStoreCompat.setCaptureStrategy(CaptureStrategy(true, authority))
        mediaStoreCompat.dispatchCaptureIntent(activity, requestCode)
        return mediaStoreCompat
    }

    /**
     * 获取图片地址
     * 只获取一张
     */
    fun getImgPath(data: Intent): Observable<String> {
        return Observable.fromIterable(Matisse.obtainPathResult(data))
                .take(1)
    }

    /**
     * 获取多张图片
     *
     * @param data
     * @return
     */
    fun getImgPaths(data: Intent): Observable<String> {
        return Observable.fromIterable(Matisse.obtainPathResult(data))
    }
}
