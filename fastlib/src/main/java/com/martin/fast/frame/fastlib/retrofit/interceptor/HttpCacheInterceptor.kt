package com.martin.fast.frame.fastlib.retrofit.interceptor

import com.martin.fast.frame.fastlib.util.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ：Martin
 * @date : 2018/6/21 17:41
 */
class HttpCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //没网强制从缓存读取
        if (!NetworkUtil.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }

        val originalResponse = chain.proceed(request)
        if (NetworkUtil.isConnected()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            val cacheControl = request.cacheControl().toString()
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build()
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build()
        }
    }

}