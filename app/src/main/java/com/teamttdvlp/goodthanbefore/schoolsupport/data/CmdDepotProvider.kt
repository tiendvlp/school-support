package com.teamttdvlp.goodthanbefore.schoolsupport.data.responsitory

import com.google.firebase.firestore.DocumentReference
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ICmdDepotProvider

class CmdDepotProvider : ICmdDepotProvider {
    private var mFirebaseCmdDepot : ArrayList<DocumentReference>

    constructor() {
        mFirebaseCmdDepot = ArrayList()
    }
    override fun getFirebaseCmdDepot(): ArrayList<DocumentReference> {
        return mFirebaseCmdDepot
    }
}