package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithFacebook
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class LoginWithFacebook : ILoginWithFacebook {
    private var mAuth : FirebaseAuth;

    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun login(token: AccessToken, callback : LoginEvent) {
        val mCredential : AuthCredential = FacebookAuthProvider.getCredential(token.token)
        val mLoginAction:ILoginWithCredential = LoginWithCredential()
        mLoginAction.login(mCredential, callback)
    }
}