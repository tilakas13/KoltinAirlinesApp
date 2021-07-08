package com.apps.tilak.airlines.presentation.common

import android.util.Log
import com.tilak.apps.airlines.BuildConfig
import javax.inject.Inject

class Logger
@Inject constructor() {

    fun printLog(tagName: String, message: String? = "NULL value") {
        if (BuildConfig.DEBUG) {
            Log.d(tagName, message + "")
        }
    }
}