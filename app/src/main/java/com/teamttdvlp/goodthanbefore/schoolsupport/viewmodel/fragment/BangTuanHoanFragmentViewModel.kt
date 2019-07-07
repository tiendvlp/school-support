package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.process.ChemicalElementDataSpawner

class BangTuanHoanFragmentViewModel(app : Application) : AndroidViewModel(app) {

    val dataSpawner = ChemicalElementDataSpawner(app)

    fun spawnData () : ArrayList<IChemicalElement>{
        return dataSpawner.spawnData()
    }

}