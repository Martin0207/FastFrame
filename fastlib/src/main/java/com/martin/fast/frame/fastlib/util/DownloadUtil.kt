package com.martin.fast.frame.fastlib.util

import android.app.Activity
import android.os.Environment
import com.martin.fast.frame.fastlib.FastLib
import com.martin.fast.frame.fastlib.retrofit.NetUtil
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * @author ：Martin
 * @date : 2018/6/22 7:55
 */
object DownloadUtil {

    val map = HashMap<String, Disposable>()

    /**
     * 文件保存地址
     */
    val dirPath = Environment.getExternalStorageDirectory().path + File.separator + FastLib.context.packageName + File.separator

    /**
     * 下载文件
     */
    fun download(url: String, activity: Activity): String {
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdir()
        }
        val file = File(dirPath, getUrlFileName(url))
        if (file.exists()) {
            file.delete()
        }

        val disposable = NetUtil.getApi()
                .downloadFile(url)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtil.bindToLifecycle(activity))
                .subscribe {
                    FileUtils.writeFile(it.byteStream(), file.path)
                }

        map[url] = disposable

        return file.path
    }

    /**
     * 关闭下载
     */
    fun cancelDownload(url: String) {
        if (map.containsKey(url)) {
            map[url]?.dispose()
            map.remove(url)
        }
    }

    /**
     * 通过 ‘？’ 和 ‘/’ 判断文件名
     */
    private fun getUrlFileName(url: String): String {
        val index = url.lastIndexOf('?')
        val filename: String
        if (index > 1) {
            filename = url.substring(url.lastIndexOf('/') + 1, index)
        } else {
            filename = url.substring(url.lastIndexOf('/') + 1)
        }
        return filename
    }

}