package com.teamttdvlp.goodthanbefore.schoolsupport.support

import android.util.Log

fun Any.logError(mess_name : String, message : Any) {
    Log.e(mess_name, message.toString())
}

fun Any.logError (message : String) {
    if (message.contains(":")) {
        val indexOfDoubleComma = message.indexOf(":")
        Log.e(message.substring(0, indexOfDoubleComma), message.substring(indexOfDoubleComma + 1))
    } else {
        Log.e("Message: ", message)
    }
}