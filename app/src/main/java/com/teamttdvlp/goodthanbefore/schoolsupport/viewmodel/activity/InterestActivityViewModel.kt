package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.IInterestDownload
import com.teamttdvlp.goodthanbefore.schoolsupport.model.functions.InterestDownload
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserInterest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SetUserInterestEvent
import java.lang.Exception

const val ONE_MEGABYTE : Long = 1024 * 1024

class InterestActivityViewModel (var app : Application): AndroidViewModel (app) {

    var interest_list_ld = MutableLiveData<ArrayList<Interest>>()

    var ld = MutableLiveData<Drawable>()

    var dataReceiver = ArrayList<Interest>()

    var mInterestDownload : IInterestDownload = InterestDownload(app)

    var mUserInterest = UserInterest()

    fun loadData (onLoadSuccess : (interst : ArrayList<Interest>) -> Unit, onLoadFailed: (Exception) -> Unit) {
        mInterestDownload.loadInterest(onLoadSuccess, onLoadFailed)
    }

    fun setUserInterest (userId : String, interests:ArrayList<String>, callback : SetUserInterestEvent) {
        mUserInterest.setUserInterest(userId,interests, callback)
    }

}