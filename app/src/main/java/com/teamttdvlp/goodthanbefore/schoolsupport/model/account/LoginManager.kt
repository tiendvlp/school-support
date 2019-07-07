package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import android.util.Log
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.teamttdvlp.goodthanbefore.schoolsupport.data.account.LoginWithEmail
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ILoginWithEmail
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithFacebook
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithGoogle
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserInterestEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import java.lang.Exception

class LoginManager : LoginEvent, WriteInfoEvent, GetUserInterestEvent, ReadInfoEvent {

    private var loginNormally : ILoginWithEmail
    private var loginGoogle : ILoginWithGoogle
    private var loginFacebook : ILoginWithFacebook
    private var signUp : SignUp
    private var userManager:UserManager
    private var keepMeLogin:KeepMeLogin
    private var onGetUserInterestEventListener: GetUserInterestEvent? = null
    var onLoginEvent : LoginEvent? = null

    constructor() {
        keepMeLogin = KeepMeLogin()
        signUp = SignUp()
        userManager = UserManager()
        loginFacebook = LoginWithFacebook()
        loginNormally = LoginWithEmail()
        loginGoogle = LoginWithGoogle()

        userManager.getUserInterestListener = this
        userManager.setUserInfoListener = this
        userManager.getUserInfoListener = this
    }

    fun keepMeLogin () : Boolean {
        val result : User? = keepMeLogin.keepMeLogin()
        if (result!=null) {
            onLoginSuccess(result)
            return true
        } else {
            return false
        }
    }

    fun loginWithGoogle (account:GoogleSignInAccount) {
        loginGoogle.login(account, this)
    }

    fun loginWithFacebook (accessToken:AccessToken) {
        loginFacebook.login(accessToken, this)
    }

//    fun loginNormally (email:String, password:String) {
//        loginNormally.login(email, password, this)
//    }

    fun signUp (email:String, password: String, displayName:String) {
        signUp.signup(email, password,displayName, this)
    }

    private lateinit var currentUser : User
    override fun onLoginSuccess(user: User) {
        Log.d("sukien", "onLoginSuccess")
        this.currentUser = user
        userManager.getInfo(user.Id)
    }

    override fun onReadInfoSuccess(user: User?) {
        Log.d("sukien", "onReadInfSuccess")
        if (user != null) {
            user.DisplayName = currentUser.DisplayName
            user.Avatar = currentUser.Avatar
            this.currentUser = user
        }
        userManager.writeInfo(currentUser)
    }

    override fun onReadInfoFailed(e: Exception?) {
        Log.d("sukien", "onReadInfFailed")
        onLoginEvent?.onLoginFailed(e)
    }

    override fun onWriteInfoSuccess() {
        Log.d("sukien", "onWriteInfoSuccess")
        userManager.getInterests(currentUser.Id)
    }

    override fun onGetUserInterestSuccess(results: ArrayList<String>) {
        Log.d("sukien", "onGetUserInterestSuccess" + results.size.toString())
        currentUser.Interests.addAll(results)
        onLoginEvent?.onLoginSuccess(currentUser)
    }

    override fun onLoginFailed(e: Exception?) {
        Log.d("sukien", "onLoginFailed")
        onLoginEvent?.onLoginFailed(e)
    }

    override fun onWriteInfoFailed(e: Exception?) {
        Log.d("sukien", "onWriteInfoFailed")
        onLoginEvent?.onLoginFailed(e)
    }

    override fun onGetUserInterestFailed(e: Exception?) {
        Log.d("sukien", "onGetUserInterestFailed")
        onLoginEvent?.onLoginFailed(e)
    }

}