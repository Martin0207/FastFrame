package com.martin.fast.frame.fastlib.contract.impl

import com.google.gson.JsonArray
import com.martin.fast.frame.fastlib.FastLib
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.net.URLEncoder

/**
 * @author ：Martin
 * @date : 2018/6/21 16:41
 * @description : 网络请求Logger实例
 */
class HttpLogImpl : HttpLoggingInterceptor.Logger {

    override fun log(message: String?) {
        //非Debug情况下，不打印日志
        if (!FastLib.DEBUG || message == null) return
        try {
            //如果是Json数据，则以Json格式打印，便于查看
            if (message.startsWith("{")) {
                Timber.e(JSONObject(message).toString(2))
            } else if (message.startsWith("[")) {
                Timber.e(JSONArray(message).toString(2))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Timber.e(message)
        }
    }

}