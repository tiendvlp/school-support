package com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass

import java.lang.Exception

class Result<T> {
    var isSuccess : Boolean = false; get private set
    var isFailed : Boolean = false; get private set
    var result : T? = null; get private set
    var exception : Exception? = null; get private set

    fun success (value:T?) {
        result = value
        isFailed = false
        isSuccess =  true
    }

    fun failed (e:Exception?) {
        exception = e
        isSuccess = false
        isFailed = true
    }
}