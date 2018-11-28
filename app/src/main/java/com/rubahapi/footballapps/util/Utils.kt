package com.rubahapi.footballapps.util

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

@SuppressLint("SimpleDateFormat")
fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun toSimpleTimeString(date:Date?):String? = with(date ?: Date()){
    val sdf = SimpleDateFormat("HH:mm")
    sdf.timeZone = (TimeZone.getTimeZone("GMT+07:00"))
    sdf.format(this)
}