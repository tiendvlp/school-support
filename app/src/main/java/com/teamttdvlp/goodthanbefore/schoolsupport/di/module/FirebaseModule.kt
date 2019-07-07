package com.teamttdvlp.goodthanbefore.schoolsupport.di.module

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FirebaseModule {

    @Inject
    constructor() {

    }

    @Provides
    fun getFirebaseFirestore () : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}