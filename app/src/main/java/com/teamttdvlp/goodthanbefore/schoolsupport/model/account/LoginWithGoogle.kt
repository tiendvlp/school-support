package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithGoogle
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class LoginWithGoogle : ILoginWithGoogle {
    private val mAuth : FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    override fun login(account: GoogleSignInAccount, callback:LoginEvent) {
        val mLoginAction: ILoginWithCredential = LoginWithCredential()
        var mCredential:AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        mLoginAction.login(mCredential, callback)
    }

}