package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.WatchCeInfoFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.BangTuanHoanActivityViewModel

class WatchCEInfoFragment : Fragment() {
    
    lateinit var mBinding : WatchCeInfoFragmentBinding

    lateinit var activityViewModel : BangTuanHoanActivityViewModel

    var sentChemicalELement : ChemicalElement? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_ce_info, container, false)
        sentChemicalELement = arguments!!.getParcelable("chemical_element")
        if (sentChemicalELement != null) {
            mBinding.chemicalElement = sentChemicalELement
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    fun addControls () {
        activityViewModel = getViewModel()
        activityViewModel.chosenChemicalElement.observe(this, Observer {
            if (sentChemicalELement == null) {
                mBinding.chemicalElement = it
            } else {
                sentChemicalELement = null
            }
        })
    }

    fun addEvents () {

    }

}