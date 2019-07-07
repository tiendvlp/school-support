package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement

/**
 * @see BangTuanHoanActivity
 * @see FragmentWatchCEInfo
 * @see FragmentBangTuanHoang
 */
class BangTuanHoanActivityViewModel(app : Application) : AndroidViewModel(app) {
    var searchCEListData = MutableLiveData<ArrayList<ChemicalElement>>()
    var chosenChemicalElement = MutableLiveData<ChemicalElement>()

}