package com.martin.fast.frame.fastframe.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastframe.ui.adapter.RetrofitPhotoRvAdapter
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.base.BaseResponse
import com.martin.fast.frame.fastlib.contract.interfacies.OnClickOfPositionListener
import com.martin.fast.frame.fastlib.entity.ShowImageEntity
import com.martin.fast.frame.fastlib.entity.UploadEntity
import com.martin.fast.frame.fastlib.retrofit.DefaultObserver
import com.martin.fast.frame.fastlib.ui.activity.ShowImageActivity
import com.martin.fast.frame.fastlib.util.DownloadUtil
import com.martin.fast.frame.fastlib.util.PhotoUtil
import com.martin.fast.frame.fastlib.util.UploadUtil
import com.zhihu.matisse.Matisse
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_retrofit.*
import me.jessyan.progressmanager.ProgressListener
import me.jessyan.progressmanager.ProgressManager
import me.jessyan.progressmanager.body.ProgressInfo
import timber.log.Timber
import java.lang.Exception

class RetrofitActivity : BaseActivity() {

    val CODE_PHOTO = 100;

    lateinit var adapter: RetrofitPhotoRvAdapter

    val fileUrl = "https://download.fir.im/apps/5b07b7e1ca87a84b6c76aec2/install?download_token=6b7b2fdcb14afa31a6c79b46b0e41063&release_id=5b1f31ecca87a852dfe07c4c"

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, RetrofitActivity::class.java))
        }
    }

    override fun layoutRes() = R.layout.activity_retrofit

    override fun init(saveInstanceState: Bundle?) {

        setTitle("retrofit")

        rv.layoutManager = GridLayoutManager(getContext(), 3)
        adapter = RetrofitPhotoRvAdapter(getContext())
        rv.adapter = adapter

        btn_photo.setOnClickListener {
            PhotoUtil.pickPhoto(getActivity(), CODE_PHOTO, 3, false)
        }

        btn_upload.setOnClickListener {
            UploadUtil.uploadFiles(adapter.data, getActivity())
                    .subscribe(object : DefaultObserver<BaseResponse<ArrayList<UploadEntity>>>(getActivity()) {
                        override fun onSuccess(response: BaseResponse<ArrayList<UploadEntity>>) {
                        }
                    })
        }

        btn_download.setOnClickListener {
            DownloadUtil.download(fileUrl, getActivity())
        }

        btn_cancel_download.setOnClickListener {
            DownloadUtil.cancelDownload(fileUrl)
        }

        ProgressManager.getInstance().addResponseListener(fileUrl, object : ProgressListener {
            override fun onError(id: Long, e: Exception?) {

            }

            override fun onProgress(progressInfo: ProgressInfo?) {
                pb.progress = (progressInfo!!.currentbytes * 100 / progressInfo.contentLength).toInt()
                Timber.e("current progress is ${pb.progress}")
            }
        })

        adapter.onClickOfPositionListener = object : OnClickOfPositionListener {
            override fun onClick(position: Int) {
                Observable.fromIterable(adapter.data)
                        .map { ShowImageEntity(it) }
                        .collect({
                            ArrayList<ShowImageEntity>()
                        }) { list, entity ->
                            list.add(entity)
                        }
                        .subscribe(Consumer {
                            ShowImageActivity.start(getContext(), it, position)
                        })
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CODE_PHOTO -> {
                    adapter.refreshRes(Matisse.obtainPathResult(data))
                }
            }
        }
    }


}
