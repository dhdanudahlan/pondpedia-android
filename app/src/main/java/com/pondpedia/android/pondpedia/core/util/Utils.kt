package com.pondpedia.android.pondpedia.core.util

import android.util.Log
import com.pondpedia.android.pondpedia.core.util.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}