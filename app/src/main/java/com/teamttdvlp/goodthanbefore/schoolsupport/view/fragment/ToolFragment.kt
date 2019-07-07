package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.BanTuanHoanActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.OnlineSearchChemicalEquationActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SpecialGraphMenuActivity
import kotlinx.android.synthetic.main.fragment_tool.*


class ToolFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tool, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_bangtuanhoan.setOnClickListener {
            startActivity(Intent(activity!!, BanTuanHoanActivity::class.java))
        }

        btn_search_chem_eq.setOnClickListener {
            startActivity(Intent(activity!!, OnlineSearchChemicalEquationActivity::class.java))
        }

        btn_khao_sat_do_thi.setOnClickListener {
            startActivity(Intent(activity!!, SpecialGraphMenuActivity::class.java))
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onClick(v: View?) {

    }

    interface Aladu {

    }
}
