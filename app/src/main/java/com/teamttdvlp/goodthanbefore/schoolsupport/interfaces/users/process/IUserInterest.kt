package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserInterestEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SetUserInterestEvent
import java.lang.Exception

interface IUserInterest {
    fun getUserInterest(userId:String, callback:GetUserInterestEvent)
    fun setUserInterest(userId:String, interests:ArrayList<String>, callback:SetUserInterestEvent)
}

interface IInterestDownloadManager {
    fun loadInterest(onAnImageLoadSuccess : (interst : ArrayList<Interest>) -> Unit,
                     onGetCollectionFailed: (Exception) -> Unit )
}