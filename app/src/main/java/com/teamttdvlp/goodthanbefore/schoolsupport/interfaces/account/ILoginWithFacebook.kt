package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.facebook.AccessToken
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

interface ILoginWithFacebook {
    fun login (token : AccessToken, callback:LoginEvent)
}