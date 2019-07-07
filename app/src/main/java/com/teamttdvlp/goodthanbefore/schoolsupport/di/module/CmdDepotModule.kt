package com.teamttdvlp.goodthanbefore.schoolsupport.di.component

import com.teamttdvlp.goodthanbefore.schoolsupport.data.responsitory.CmdDepotProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ICmdDepotProvider
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class CmdDepotModule {
    @Inject
    constructor() {}

    @UnitOfWorkScope
    @Provides
    fun getCmdDepotProvider () : ICmdDepotProvider {
        return CmdDepotProvider()
    }
}