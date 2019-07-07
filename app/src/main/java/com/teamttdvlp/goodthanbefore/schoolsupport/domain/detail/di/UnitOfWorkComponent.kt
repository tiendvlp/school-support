package com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.di

import com.teamttdvlp.goodthanbefore.schoolsupport.di.component.CmdDepotModule
import com.teamttdvlp.goodthanbefore.schoolsupport.di.component.ResponsitoryModule
import com.teamttdvlp.goodthanbefore.schoolsupport.di.component.UnitOfWorkScope
import com.teamttdvlp.goodthanbefore.schoolsupport.di.module.FirebaseModule
import com.teamttdvlp.goodthanbefore.schoolsupport.di.module.SaveAlgorithmModule
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.savealgorithm.ISaveAlgorithm
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.IUserResponsitory
import dagger.Component

@UnitOfWorkScope
@Component(modules = [CmdDepotModule::class, FirebaseModule::class, ResponsitoryModule::class, SaveAlgorithmModule::class])
interface UnitOfWorkComponent {
    fun getUserResponsitory () : IUserResponsitory
    fun getSave () : ISaveAlgorithm
}