package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import io.reactivex.Single

interface IUserDb {
    fun addUser(user: User, callback: WriteInfoEvent)
    fun getUser(userId:String):Single<User>
}
