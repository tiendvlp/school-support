package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SetUserInterestEvent
import io.reactivex.Single

interface IUserInterestDb {
    fun getUserInterest(userId: String) : Single<ArrayList<String>>
    fun addUserInterest(userId: String, interests: ArrayList<String>, callback: SetUserInterestEvent)
}