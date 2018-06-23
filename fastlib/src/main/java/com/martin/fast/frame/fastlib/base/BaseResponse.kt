package com.martin.fast.frame.fastlib.base

import android.text.TextUtils

/**
 * @author martin
 */
class BaseResponse<T> {

    var errorCode: Int = 0
    var msg: String? = null
        get() = if (TextUtils.isEmpty(field)) if (isSuccess) "网络访问成功" else "网络访问失败" else field
    var data: T? = null
    var isSuccess: Boolean = false

    override fun toString(): String {
        return "BaseResponse(errorCode=$errorCode, data=$data, isSuccess=$isSuccess)"
    }


}
