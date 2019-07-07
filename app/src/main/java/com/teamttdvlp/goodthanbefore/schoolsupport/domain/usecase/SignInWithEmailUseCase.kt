package com.teamttdvlp.goodthanbefore.schoolsupport.domain.usecase

import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.di.DaggerUnitOfWorkComponent
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.di.UnitOfWorkComponent
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.port.ISignInWithEmailUseCase
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ILoginWithEmail
import io.reactivex.Single
import javax.inject.Inject

class SignInWithEmailUseCase : ISignInWithEmailUseCase {
    private var mEmailLogin : ILoginWithEmail
    private var mUof : UnitOfWorkComponent

    @Inject
    constructor(emailLogin: ILoginWithEmail) {
        mEmailLogin = emailLogin
        mUof = DaggerUnitOfWorkComponent.create()
    }

    override fun loginWithEmail (email:String, password:String) : Single<com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User> {
        var obs = mEmailLogin.login(email, password)
            .flatMap {

                return@flatMap mUof.getUserResponsitory().getUser(it.Id)
            }
        return obs
    }
}