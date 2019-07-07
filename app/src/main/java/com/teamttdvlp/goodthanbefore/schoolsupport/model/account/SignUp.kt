package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class SignUp {
    private lateinit var mAuth: FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    fun signup(email: String, password: String,displayName:String, callback: LoginEvent) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                var user = User()
                user.DisplayName = displayName
                user.Id = it.result!!.user.uid
                val newProfile = UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.EMPTY)
                    .build()
                it.result!!.user.updateProfile(newProfile).addOnCompleteListener {
                    if (it.isSuccessful) {
                        callback.onLoginSuccess(user)
                    } else {
                        Log.d("sukien", "new profile failed")
                        callback.onLoginFailed(it.exception)
                    }
                }
            } else {
                Log.d("sukien", "signup failed: ${it.exception!!.message}")
                callback.onLoginFailed(it.exception)
            }
        }
    }
}