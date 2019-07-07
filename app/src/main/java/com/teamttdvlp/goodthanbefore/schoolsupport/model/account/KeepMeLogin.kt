package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.IKeepMeLogin
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class KeepMeLogin : IKeepMeLogin {
    private var mAuth : FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    override fun keepMeLogin() : User? {
        if (mAuth.currentUser != null) {
            var user = User()
            user.Id = mAuth.currentUser!!.uid
            user.DisplayName = mAuth.currentUser!!.displayName!!
            user.Avatar = mAuth.currentUser!!.photoUrl.toString()
            Log.d("KeepMeLogin", user.DisplayName)
            Log.d("KeepMeLogin", user.Avatar)
            return user
        } else {
            return null
        }
    }
}