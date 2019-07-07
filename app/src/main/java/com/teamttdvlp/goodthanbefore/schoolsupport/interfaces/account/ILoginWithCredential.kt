package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.google.firebase.auth.AuthCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

interface ILoginWithCredential {
    fun login (credential: AuthCredential, callback:LoginEvent)
}