package com.martin.fast.frame.fastlib.retrofit

import android.app.Activity
import android.os.NetworkOnMainThreadException
import android.text.TextUtils

import com.google.gson.JsonParseException
import com.martin.fast.frame.fastlib.R
import com.martin.fast.frame.fastlib.base.BaseResponse
import com.martin.fast.frame.fastlib.util.TipDialogUtil
import com.martin.fast.frame.fastlib.util.ToastUtil
import com.orhanobut.logger.Logger

import org.json.JSONException

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import retrofit2.HttpException

import io.reactivex.SingleObserver


/**
 * @author martin
 */
abstract class DefaultSingleObserver<T : BaseResponse<*>>(activity: Activity,
                                                          isShowLoading: Boolean = true)
    : SingleObserver<T> {

    init {
        if (isShowLoading) {
            TipDialogUtil.showLoadingDialog(activity)
        }
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onSuccess(t: T) {
        dismissProgress()
        if (t.isSuccess) {
            onSucceed(t)
        } else {
            onFail(t)
        }
    }

    abstract fun onSucceed(response: T)

    private fun dismissProgress() {
        TipDialogUtil.dismissDialog()
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
//    abstract fun onSuccess(response: T)

    /**
     * 服务器返回数据，但success为false
     *
     * @param response 服务器返回的数据
     */
    fun onFail(response: T) {
        val message = response.msg
        if (TextUtils.isEmpty(message)) {
            ToastUtil.show(R.string.response_return_error)
        } else {
            ToastUtil.show(message!!)
        }
    }

    override fun onError(e: Throwable) {
        Logger.e(e.message!!)
        dismissProgress()
        if (e is HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
                || e is UnknownHostException
                || e is OnErrorNotImplementedException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR)
        } else if (e is NetworkOnMainThreadException) { //网络请求不可以放在主线程
            onException(ExceptionReason.NETWORK_ON_MAIN_THREAD)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
    }


    /**
     * 请求异常
     */
    fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.CONNECT_ERROR -> ToastUtil.show(R.string.connect_error)
            ExceptionReason.CONNECT_TIMEOUT -> ToastUtil.show(R.string.connect_timeout)
            ExceptionReason.BAD_NETWORK -> ToastUtil.show(R.string.bad_network)
            ExceptionReason.PARSE_ERROR -> ToastUtil.show(R.string.parse_error)
            ExceptionReason.NETWORK_ON_MAIN_THREAD -> ToastUtil.show(R.string.Network_On_Main_Thread_Exception)
            ExceptionReason.UNKNOWN_ERROR -> ToastUtil.show(R.string.unknown_error)
            else -> ToastUtil.show(R.string.unknown_error)
        }
    }

}
