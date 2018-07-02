package com.martin.fast.frame.fastlib.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.martin.fast.frame.fastlib.R
import com.martin.fast.frame.fastlib.base.BaseFragment
import com.martin.fast.frame.fastlib.constant.ConstantExtra
import com.martin.fast.frame.fastlib.entity.ShowImageEntity
import com.martin.fast.frame.fastlib.util.image.load.ImageLoader
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase
import kotlinx.android.synthetic.main.fragment_show_image.*

/**
 * @author ：Martin
 * @date : 2018/6/23 16:12
 */
class ShowImageFragment : BaseFragment() {

    companion object {
        fun getFragment(imgEntity: ShowImageEntity): ShowImageFragment {
            val fragment = ShowImageFragment()
            val bundle = Bundle()
            bundle.putParcelable(ConstantExtra.DATA, imgEntity)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutRes() = R.layout.fragment_show_image

    override fun initData(saveInstanceState: Bundle?) {
        img.displayType = ImageViewTouchBase.DisplayType.FIT_TO_SCREEN

        val imgModel = arguments?.getParcelable<ShowImageEntity>(ConstantExtra.DATA)!!

        Glide.with(getFragment())
                .load(imgModel.path ?: imgModel.uri)
                .into(img)
    }

}