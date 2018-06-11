package com.martin.fast.frame.fastlib.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author ：Martin
 * @date : 2018/6/8 13:08
 */
class CustomFragmentPagerAdapter(fragmentManager: FragmentManager, var data: ArrayList<Fragment>)
    : FragmentPagerAdapter(fragmentManager) {

    var titles: ArrayList<String>? = null

    override fun getItem(position: Int): Fragment = data.get(position)

    override fun getCount(): Int = data.size

    override fun getPageTitle(position: Int): CharSequence? = titles?.get(position)
}
