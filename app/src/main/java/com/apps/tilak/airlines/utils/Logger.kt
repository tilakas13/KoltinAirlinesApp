package com.apps.tilak.airlines.utils

import android.util.Log

object Logger {

    fun printLog(tagName: String, message: String? = "NULL value") {
        Log.d(tagName, message + "")
    }
}