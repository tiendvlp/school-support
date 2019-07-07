package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class LoginWithCredential : ILoginWithCredential {
    private var mAuth : FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun login(credential: AuthCredential, callback: LoginEvent) {
        mAuth.signInWithCredential(credential).addOnCompleteListener({
            if (it.isSuccessful) {
                val result = User()
                result.Id = it.result!!.user.uid
                result.Avatar = it.result!!.user!!.photoUrl.toString()
                result.DisplayName = it.result!!.user!!.displayName!!
                callback.onLoginSuccess(result)
            } else {
                callback.onLoginFailed(it.exception)
            }
        })
    }

}