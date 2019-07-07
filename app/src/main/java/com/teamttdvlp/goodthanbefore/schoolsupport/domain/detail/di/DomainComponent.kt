package com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.di

import com.teamttdvlp.goodthanbefore.schoolsupport.data.account.AccountModule
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.usecase.SignInWithEmailUseCase
import dagger.Component

@Component(modules = [AccountModule::class])
interface DomainComponent {
    fun getLoginWithEmail() : SignInWithEmailUseCase
}