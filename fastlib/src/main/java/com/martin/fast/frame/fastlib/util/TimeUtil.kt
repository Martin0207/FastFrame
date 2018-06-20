package com.martin.fast.frame.fastlib.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author ：Martin
 * @date : 2018/6/20 10:54
 */
@SuppressLint("SimpleDateFormat")
object TimeUtil {

    /**
     * 日期时间格式,全局统一
     */
    private const val FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"

    /**
     * 日期格式
     */
    private const val FORMAT_DATE = "yyyy_MM-dd"

    /**
     * 时间格式
     */
    private const val FORMAT_TIME = "HH:mm:ss"

    /**
     * 当前时间
     */
    fun currentTimeMillis() = System.currentTimeMillis()

    /**
     * 将时间毫秒数转化为固定 日期时间格式
     */
    fun datetimeForTimeMillis(time: Long) = SimpleDateFormat(FORMAT_DATETIME).format(time)!!

    /**
     * 获取当前格式时间
     */
    fun currentDatetime() = datetimeForTimeMillis(currentTimeMillis())

    /**
     * 将时间毫秒数转为固定 日期格式
     */
    fun dateForTimeMillis(time: Long) = SimpleDateFormat(FORMAT_DATE).format(time)

    /**
     * 固定时间格式
     */
    fun timeForTimeMillis(time: Long) = SimpleDateFormat(FORMAT_TIME).format(time)!!

    /**
     * 当前年份
     */
    fun currentYear() = Calendar.getInstance().get(Calendar.YEAR)

    /**
     * 当前月份
     */
    fun currentMonth() = Calendar.getInstance().get(Calendar.MONTH)

    /**
     * 当前周数,相对于月
     * @Tip : 周数与天数 都有相对于年的计数概念
     */
    fun currentWeek() = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH)

    /**
     * 当前天数,相对于 月
     */
    fun currentDay() = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    /**
     * 小时数 , 相对于 当天
     */
    fun currentHour() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    /**
     * 分钟数
     */
    fun currentMinute() = Calendar.getInstance().get(Calendar.MINUTE)

    /**
     * 秒数
     */
    fun currentSecond() = Calendar.getInstance().get(Calendar.SECOND)

    /**
     * 根据日期时间 获取毫秒数
     */
    fun millisForDatetime(datetime: String) = SimpleDateFormat(FORMAT_DATETIME).parse(datetime).time

    /**
     * 根据日期 获取毫秒数
     */
    fun millisForDate(date: String) = SimpleDateFormat(FORMAT_DATE).parse(date).time


}

