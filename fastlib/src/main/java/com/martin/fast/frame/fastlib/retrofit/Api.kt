package com.martin.fast.frame.fastlib.retrofit

import com.martin.fast.frame.fastlib.base.BaseResponse
import com.martin.fast.frame.fastlib.entity.UploadEntity
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * @author ：Martin
 * @date : 2018/6/21 20:44
 */
interface Api {

    companion object {
        val BASE_URL = "http://www.bnzyll.com/"
    }

    /**
     * 下载文件
     *
     * @param url 下载地址
     */
    @Streaming
    @GET
    fun downloadFile(@Url url: String): Observable<ResponseBody>

    //================================= 上传文件 ==============================================

    /**
     * 上传单个文件
     */
    @Multipart
    @POST("api/upload/android")
    fun uploadFile(@Part("file") file: @JvmSuppressWildcards RequestBody): Observable<BaseResponse<ArrayList<UploadEntity>>>

    @Multipart
    @POST("api/upload/android")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<BaseResponse<ArrayList<UploadEntity>>>

    /**
     * 上传多个文件
     */
    @Multipart
    @POST("api/upload/android")
    fun uploadFiles(@PartMap maps: Map<String, @JvmSuppressWildcards RequestBody>)
            : @JvmSuppressWildcards Observable<BaseResponse<ArrayList<UploadEntity>>>


}