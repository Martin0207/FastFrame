package com.martin.fast.frame.fastlib.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @date : 2018/6/7 16:10
 */
abstract class BaseListViewAdapter<T>(var context: Context, var data: ArrayList<T> = ArrayList()) : BaseAdapter() {

    var inflater: LayoutInflater = LayoutInflater.from(context)


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

    abstract override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View

    override fun getItem(position: Int): T = data.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = data.size

}