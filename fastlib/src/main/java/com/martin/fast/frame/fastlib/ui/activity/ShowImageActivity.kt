package com.martin.fast.frame.fastlib.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.WindowManager
import com.martin.fast.frame.fastlib.R
import com.martin.fast.frame.fastlib.base.BaseActivity
import com.martin.fast.frame.fastlib.constant.ConstantExtra
import com.martin.fast.frame.fastlib.entity.ShowImageEntity
import com.martin.fast.frame.fastlib.ui.adapter.CustomFragmentPagerAdapter
import com.martin.fast.frame.fastlib.ui.fragment.ShowImageFragment
import com.martin.fast.frame.fastlib.util.RxLifecycleUtil
import io.reactivex.Observable
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_show_image.*
import java.util.concurrent.Callable

class ShowImageActivity : BaseActivity() {

    /**
     * 启动方式
     */
    companion object {
        fun start(context: Context, path: String) {
            start(context, ShowImageEntity(path))
        }

        fun start(context: Context, imgEntity: ShowImageEntity) {
            start(context, arrayListOf(imgEntity))
        }

        fun start(context: Context, imgList: ArrayList<ShowImageEntity>, positionShow: Int = 0) {
            val intent = Intent(context, ShowImageActivity::class.java)
            intent.putExtra(ConstantExtra.POSITION, positionShow)
            intent.putExtra(ConstantExtra.DATA_LIST, imgList)
            context.startActivity(intent)
        }
    }

    override fun layoutRes() = R.layout.activity_show_image

    override fun init(saveInstanceState: Bundle?) {

        img_close.setOnClickListener { finish() }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        val positionShow = intent.getIntExtra(ConstantExtra.POSITION, 0)

        val imgs = intent.getParcelableArrayListExtra<ShowImageEntity>(ConstantExtra.DATA_LIST)

        Observable.fromIterable(imgs)
                .compose(RxLifecycleUtil.bindToLifecycle(getActivity()))
                .filter {
                    it.path != null || it.uri != null
                }
                .map {
                    ShowImageFragment.getFragment(it)
                }
                .collect({
                    ArrayList<Fragment>()
                }) { list, fragment ->
                    list.add(fragment)
                }
                .map {
                    CustomFragmentPagerAdapter(supportFragmentManager, it)
                }
                .subscribe(Consumer {
                    vp.adapter = it
                    if (vp.childCount > positionShow) {
                        vp.setCurrentItem(positionShow, false)
                    }
                })
    }


}
