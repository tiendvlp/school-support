package com.teamttdvlp.goodthanbefore.schoolsupport.support

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateSupport {
    companion object {
    fun getDateByTimeMillis (millis : Long) : String {
        var date = Date(millis)
        var format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

    fun getCurrentDate () : String {
        var date = Date()
        var format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }
    }
    }