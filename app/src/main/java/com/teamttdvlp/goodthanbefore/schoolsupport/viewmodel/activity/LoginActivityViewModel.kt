package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.di.DaggerDomainComponent
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.di.DomainComponent
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.usecase.SignInWithEmailUseCase
import com.teamttdvlp.goodthanbefore.schoolsupport.support.CheckRegisterInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent
import io.reactivex.Single

class LoginActivityViewModel : ViewModel {
    private var mCheck: CheckRegisterInfo = CheckRegisterInfo()

    private var mSigninUseCase: SignInWithEmailUseCase
    private var mDomainComponent: DomainComponent

    constructor() {
        mDomainComponent = DaggerDomainComponent.create()
        mSigninUseCase = mDomainComponent.getLoginWithEmail()
    }

    var isLoading: MutableLiveData<Int> = MutableLiveData()

    var onLoginEvent: LoginEvent? = null

    fun checkEmail(email: String): Boolean {
        return mCheck.checkEmail(email)
    }

    fun checkPassword(password: String): Boolean {
        return mCheck.checkPassword(password)
    }

    fun checkDisplayname(displayname: String): Boolean {
        return mCheck.checkDisplayName(displayname)
    }

//    fun loginWithFacebook (accessToken:AccessToken) {
//        mLoginManager.onLoginEvent = this.onLoginEvent
//        mLoginManager.loginWithFacebook(accessToken)
//    }
//
//    fun loginWithGoogle (account:GoogleSignInAccount) {
//        mLoginManager.onLoginEvent = this.onLoginEvent
//        mLoginManager.loginWithGoogle(account)
//    }

    fun loginNormally(
        email: String,
        password: String
    ): Single<com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User> {
        return mSigninUseCase.loginWithEmail(email, password)
//    }
//
//    fun signup (email:String, password: String, displayname: String) {
//        mLoginManager.onLoginEvent = this.onLoginEvent
//        mLoginManager.signUp(email, password, displayname)
//    }

//    fun keepMeLogin () : Boolean {
//        mLoginManager.onLoginEvent = this.onLoginEvent
//        return mLoginManager.keepMeLogin()
//    }
    }
}