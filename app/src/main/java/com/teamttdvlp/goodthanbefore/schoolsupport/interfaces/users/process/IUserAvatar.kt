package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent

interface IUserAvatar {
    fun uploadAvatar(user: User, imageByteArray: ByteArray, callback : UploadAvatarEvent)
    fun getAvatar (user : User)
}