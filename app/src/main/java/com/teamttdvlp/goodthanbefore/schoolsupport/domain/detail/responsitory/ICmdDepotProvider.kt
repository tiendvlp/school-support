package com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory

import com.google.firebase.firestore.DocumentReference

interface ICmdDepotProvider {
    fun getFirebaseCmdDepot() : ArrayList<DocumentReference>
}