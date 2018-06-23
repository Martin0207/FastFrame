package com.martin.fast.frame.fastframe.ui.adapter

import android.content.Context
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.martin.fast.frame.fastframe.R
import com.martin.fast.frame.fastlib.base.BaseRecycleViewAdapter

/**
 * @author ：Martin
 * @date : 2018/6/22 10:35
 */
class RetrofitPhotoRvAdapter(context: Context)
    : BaseRecycleViewAdapter<String, RetrofitPhotoRvAdapter.Holder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitPhotoRvAdapter.Holder {
        val view = inflater.inflate(R.layout.item_retrofit_photo, parent, false)
        return Holder(view, this)
    }

    override fun onBindViewHolder(holder: RetrofitPhotoRvAdapter.Holder, position: Int) {
        Glide.with(context)
                .load(getItem(position)!!)
                .into(holder.img!!)
    }

    class Holder(item: View, adapter: BaseRecycleViewAdapter<String, Holder>)
        : BaseViewHolder<String, Holder>(item, adapter) {

        var img: ImageView? = null

        init {
            img = item.findViewById(R.id.img)
        }

    }

}