package com.martin.fast.frame.fastlib.util

import android.app.Activity

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * 作者：Martin on 2018/1/30 14:28
 * 邮箱：martin0207mfh@163.com
 */
object TipDialogUtil {

    var tipDialog: QMUITipDialog? = null
    private var disposable: Disposable? = null

    /**
     * loading默认提示
     */
    private const val loadingDefaultMsg = "请稍候……"

    /**
     * success 默认提示
     */
    private const val successDefaultMsg = "操作成功"

    /**
     * error 默认提示
     */
    private const val errorDefaultMsg = "操作失败"

    /**
     * tip / message 默认提示
     * @description : 最好不要用默认啊!
     */
    private const val defaultMsg = "tip tip"

    /**
     * loading 最大持续时间
     */
    private const val durationForLoading: Long = 8000

    /**
     * 其他tipDialog的默认持续时间
     */
    private const val durationForTip: Long = 1000

    @JvmOverloads
    fun showLoadingDialog(activity: Activity, msg: String = loadingDefaultMsg, cancelable: Boolean = true) {
        val iconType = QMUITipDialog.Builder.ICON_TYPE_LOADING
        showDialog(activity, iconType, msg, cancelable)
    }

    /**
     * 提取 show
     *
     * @param activity   上下文对象
     * @param cancelable 是否可以关闭
     * @param iconType   图标类型
     * @param tipMsg    提醒内容
     */
    private fun showDialog(activity: Activity, iconType: Int, tipMsg: String, cancelable: Boolean = true) {
        if (tipDialog != null && tipDialog!!.isShowing) {
            dismissDialog()
        }
        tipDialog = QMUITipDialog.Builder(activity)
                .setIconType(iconType)
                .setTipWord(tipMsg)
                .create(cancelable)

        if (!activity.isFinishing && !activity.isDestroyed) {
            tipDialog!!.show()

            var duration: Long = durationForTip
            if (iconType == QMUITipDialog.Builder.ICON_TYPE_LOADING) {
                duration = durationForLoading
            }

            disposable = Observable.timer(duration, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { aLong ->
                        if (tipDialog != null) {
                            dismissDialog()
                        }
                    }
        }
        setDismissListener()
    }

    /**
     * 关闭 dialog
     */
    fun dismissDialog() {
        if (tipDialog != null && tipDialog!!.isShowing) {
            tipDialog!!.dismiss()
            tipDialog = null;
        }
    }

    /**
     * 设置关闭监听
     */
    private fun setDismissListener() {
        if (tipDialog == null) {
            return
        }
        tipDialog!!.setOnDismissListener { dialog ->
            if (tipDialog != null && disposable != null) {
                //                tipDialog = null;
                disposable!!.dispose()
            }
        }
    }

    fun showLoadingDialog(activity: Activity, msgRes: Int) {
        if (msgRes <= 0) {
            throw NullPointerException("the resource of msg must not null !")
        }
        showLoadingDialog(activity, activity.resources.getString(msgRes))
    }

    @JvmOverloads
    fun showSuccessDialog(activity: Activity, tipMsg: String = successDefaultMsg, cancelable: Boolean = true) {
        val iconType = QMUITipDialog.Builder.ICON_TYPE_SUCCESS
        showDialog(activity, iconType, tipMsg, cancelable)
    }

    fun showSuccessDialog(activity: Activity, msgRes: Int) {
        if (msgRes <= 0) {
            throw NullPointerException("the resource of msg must not null !")
        }
        showSuccessDialog(activity, activity.resources.getString(msgRes))
    }

    @JvmOverloads
    fun showErrorDialog(activity: Activity, tipMsg: String = errorDefaultMsg, cancelable: Boolean = true) {
        val iconType = QMUITipDialog.Builder.ICON_TYPE_FAIL
        showDialog(activity, iconType, tipMsg, cancelable)
    }

    fun showErrorDialog(activity: Activity, msgRes: Int) {
        if (msgRes <= 0) {
            throw NullPointerException("the resource of msg must not null !")
        }
        showErrorDialog(activity, activity.resources.getString(msgRes))
    }

    @JvmOverloads
    fun showTipDialog(activity: Activity, tipMsg: String = defaultMsg, cancelable: Boolean = true) {
        val iconType = QMUITipDialog.Builder.ICON_TYPE_INFO
        showDialog(activity, iconType, tipMsg, cancelable)
    }

    fun showTipDialog(activity: Activity, msgRes: Int) {
        if (msgRes <= 0) {
            throw NullPointerException("the resource of msg must not null !")
        }
        showTipDialog(activity, activity.resources.getString(msgRes))
    }

}
