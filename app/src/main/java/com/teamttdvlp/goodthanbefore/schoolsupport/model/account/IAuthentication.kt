package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single

interface IAuthentication {
    fun keepMeLogin() : Single<User>
    fun loginWithEmailAndPassword(email: String, password: String):Single<User>
    fun signup(email: String, password: String,displayName:String) : Single<User>
    fun loginWithFacebook(token: AccessToken): Single<User>
    fun loginWithGoogle(account: GoogleSignInAccount): Single<User>
}