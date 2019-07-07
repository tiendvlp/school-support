package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

interface ILoginWithGoogle {

    fun login (account:GoogleSignInAccount, callback:LoginEvent)
}