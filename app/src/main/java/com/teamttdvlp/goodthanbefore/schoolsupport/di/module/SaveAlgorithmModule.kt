package com.teamttdvlp.goodthanbefore.schoolsupport.di.module

import com.teamttdvlp.goodthanbefore.schoolsupport.data.savealgorithm.SaveAlgorithm
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ICmdDepotProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.savealgorithm.ISaveAlgorithm
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class SaveAlgorithmModule {
    @Inject
    constructor() {}

    @Provides
    fun getSaveAlgorithm (cmd: ICmdDepotProvider) : ISaveAlgorithm {
        return SaveAlgorithm(cmd)
    }
}