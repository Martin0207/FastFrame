package com.martin.fast.frame.fastlib.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.fast.frame.fastlib.contract.interfacies.OnClickOfPositionListener

/**
 * @author ：Martin
 * @date : 2018/6/7 16:49
 */
abstract class BaseRecycleViewAdapter<T, H : BaseRecycleViewAdapter.BaseViewHolder<T, H>>(val context: Context,
                                                                                          var data: ArrayList<T> = ArrayList())
    : RecyclerView.Adapter<H>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * 点击事件监听
     */
    var ofPositionListener: OnClickOfPositionListener? = null

    fun getItem(position: Int) = if (data.size > position) data[position] else null

    /**
     * 刷新数据
     */
    fun refreshRes(list: List<T>?) {
        list?.let {
            data.clear()
            data.addAll(list)
            notifyDataSetChanged()
        }
    }

    /**
     * 新增数据
     */
    fun addRes(list: List<T>?) {
        list?.let {
            data.addAll(list)
            notifyDataSetChanged()
        }
    }

    /**
     * 新增数据
     */
    fun addRes(item: T?) {
        item?.let {
            data.add(item)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = data.size

    /**
     * 提供ViewHolder的父类 , 实现点击事件
     */
    abstract class BaseViewHolder<T, H : BaseViewHolder<T, H>>(var item: View, val adapter: BaseRecycleViewAdapter<T, H>)
        : RecyclerView.ViewHolder(item) {

        init {
            item.setOnClickListener {
                adapter.ofPositionListener?.onClick(adapterPosition)
            }
        }

    }

}