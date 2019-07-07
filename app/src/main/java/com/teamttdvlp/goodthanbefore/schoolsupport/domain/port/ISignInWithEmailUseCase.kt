package com.teamttdvlp.goodthanbefore.schoolsupport.domain.port

import com.teamttdvlp.goodthanbefore.schoolsupport.domain.respondmodel.SignInWithEmailRepondModel
import io.reactivex.Single

interface ISignInWithEmailUseCase {
    fun loginWithEmail(email: String, password: String): Single<SignInWithEmailRepondModel>
}
