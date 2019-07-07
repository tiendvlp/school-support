package com.teamttdvlp.goodthanbefore.schoolsupport.data.account

import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ILoginWithEmail
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class AccountModule {
    @Inject
    constructor()

    @Provides
    fun getLoginWithEmail () : ILoginWithEmail {
        return LoginWithEmail()
    }
}