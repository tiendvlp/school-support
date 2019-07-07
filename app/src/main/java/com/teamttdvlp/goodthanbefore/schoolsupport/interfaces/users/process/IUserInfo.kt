package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UpdateInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent

interface IUserInfo {
    fun writeInfo(user: User, callback:WriteInfoEvent)
    fun readInfo (userId:String, callback : ReadInfoEvent)
}