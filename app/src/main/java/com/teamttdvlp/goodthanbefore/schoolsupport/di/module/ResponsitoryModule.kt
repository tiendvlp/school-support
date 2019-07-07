package com.teamttdvlp.goodthanbefore.schoolsupport.di.component

import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.data.responsitory.UserResponsitory
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ICmdDepotProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.IUserResponsitory
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ResponsitoryModule {
    @Inject
    constructor() {
    }

    @UnitOfWorkScope
    @Provides
    fun getUserResponsitory (cmdDepot: ICmdDepotProvider, firestore:FirebaseFirestore) : IUserResponsitory {
        return UserResponsitory(cmdDepot, firestore)
    }
}