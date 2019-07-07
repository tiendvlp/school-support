package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.activity.ChemicalEquationActivityBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalequation.ChemicalEquation
import kotlinx.android.synthetic.main.activity_chemical_equation.*

/**
 * @see com.example.searchchemequamodule.OnlineSearchChemicalEquationActivity
 */
class ChemicalEquationActivity : AppCompatActivity() {

    lateinit var mBinding : ChemicalEquationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chemical_equation)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chemical_equation)
        var chem_eq = intent!!.extras.getParcelable(CHEMICAL_EQUATION) as ChemicalEquation
        txt_chem_equation.text = Html.fromHtml(chem_eq.equation.replace("+", " + "))

        txt_chem_condition.text = if (chem_eq.condition != "")
            Html.fromHtml(chem_eq.condition.replaceFirst(chem_eq.condition[0], chem_eq.condition[0].toUpperCase()))
                else "Không có"

        txt_chem_phenonema.text = if (chem_eq.phenonema != "")
            Html.fromHtml(chem_eq.phenonema.replaceFirst(chem_eq.phenonema[0], chem_eq.phenonema[0].toUpperCase()))
                else "Không có"

        txt_how_to_do.text = if (chem_eq.howToDo != "")
            Html.fromHtml(chem_eq.howToDo.replaceFirst(chem_eq.howToDo[0], chem_eq.howToDo[0].toUpperCase()))
                else "Không có"
    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        val CHEMICAL_EQUATION = "Chemical_Equation"
    }

}
