package com.teamttdvlp.goodthanbefore.schoolsupport.data.savealgorithm

import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ICmdDepotProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.savealgorithm.ISaveAlgorithm

class SaveAlgorithm : ISaveAlgorithm {
    private var mCmdDepot: ICmdDepotProvider

    constructor(cmd : ICmdDepotProvider) {
        mCmdDepot = cmd
    }
    override fun commit() {

    }

}