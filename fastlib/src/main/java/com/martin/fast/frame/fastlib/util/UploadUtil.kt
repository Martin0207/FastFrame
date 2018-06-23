package com.martin.fast.frame.fastlib.util

import android.app.Activity
import com.martin.fast.frame.fastlib.base.BaseResponse
import com.martin.fast.frame.fastlib.model.UploadModel
import com.martin.fast.frame.fastlib.retrofit.NetUtil
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.LinkedHashMap

/**
 * @author ：Martin
 * @date : 2018/6/22 8:26
 */
object UploadUtil {

    private val typeStr = "multipart/form-data"
    private var nameDis = 0

    /**
     * 上传多个文件
     * 推荐使用
     */
    fun uploadFiles(filePaths: List<String>, activity: Activity) =
            Observable.just(filePaths)
                    .subscribeOn(Schedulers.io())
                    .compose(RxLifecycleUtil.bindToLifecycle(activity))
                    .map {
                        val map = LinkedHashMap<String, RequestBody>()
                        for (path in it) {
                            val file = File(path)
                            if (file.exists()) {
                                val fileType = FileUtils.getFileType(file.name)
                                val fileName = fileType + "\"; fileName=\"" + fileType +
                                        nameDis + "." + FileUtils.getFileSuffix(file.name)
                                nameDis++
                                Logger.e("file name is $fileName")
                                val body = RequestBody.create(MediaType.parse(typeStr), file)
                                map.put(fileName, body)
                            }
                        }
                        map
                    }
                    .flatMap {
                        NetUtil.getApi().uploadFiles(it)
                    }


    /**
     * 上传单个文件
     *
     */
    fun uploadFile(filePath: String, activity: Activity) =
            Observable.just(filePath)
                    .subscribeOn(Schedulers.io())
                    .map {
                        val file = File(it)
                        val body = RequestBody.create(MediaType.parse(typeStr), file)
                        MultipartBody.Part.createFormData("file", file.name, body)
                    }
                    .compose(RxLifecycleUtil.bindToLifecycle(activity))
                    .flatMap {
                        NetUtil.getApi().uploadFile(it)
                    }

    /**
     * 第二种上传单个文件方法
     * 只是为了展示上传文件的方法
     */
    fun uploadFile2(filePath: String, activity: Activity) =
            Observable.just(filePath)
                    .subscribeOn(Schedulers.io())
                    .compose(RxLifecycleUtil.bindToLifecycle(activity))
                    .map {
                        File(it)
                    }
                    .map {
                        RequestBody.create(MediaType.parse(typeStr), it)
                    }
                    .flatMap {
                        NetUtil.getApi().uploadFile(it)
                    }


}