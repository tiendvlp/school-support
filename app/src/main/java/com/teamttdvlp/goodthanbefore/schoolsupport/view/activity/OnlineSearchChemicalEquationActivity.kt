package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalequation.ChemicalEquation
import com.teamttdvlp.goodthanbefore.schoolsupport.model.functions.OnReachASearch
import com.teamttdvlp.goodthanbefore.schoolsupport.model.functions.OnlineSearchChemEquaManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.removeAllSpace
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.RecyclerViewSearchCEqAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.OnlineSearchChemicalEquationActivityViewModel
import kotlinx.android.synthetic.main.activity_online_search_chemical_equation.*

/**
 * @see OnlineSearchChemicalEquationDataGetter
 */
class OnlineSearchChemicalEquationActivity : AppCompatActivity() {

    companion object {
        val EQUATION = "onl_equa"
    }

    lateinit var onlineSearchManager : OnlineSearchChemEquaManager

    lateinit var onl_sch_chem_list_adapter : RecyclerViewSearchCEqAdapter

    lateinit var viewModel : OnlineSearchChemicalEquationActivityViewModel

    lateinit var keyboardManager : InputMethodManager

    private var isNewSearch = false

    private var hasData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_search_chemical_equation)

        keyboardManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        initRecyclerView()

        initViewModel()

        addEvent()
    }

    fun addEvent () {
        onl_sch_eq_btn_search.setOnClickListener {
            var addingChemicals = onl_sch_eq_edt_adding_chems.text.toString()
            var resultChemicals = onl_sch_eq_edt_result_chems.text.toString()
            if ((addingChemicals != "") || (resultChemicals != "")) {
                onl_sch_progress_bar.visibility = View.VISIBLE
                var link = "https://phuongtrinhhoahoc.com/?chat_tham_gia="
                    .plus(addingChemicals.removeAllSpace())
                    .plus("&chat_san_pham=")
                    .plus(resultChemicals.removeAllSpace())

                isNewSearch = true
                hasData = false
                if (no_result_found.visibility == VISIBLE) no_result_found.visibility = GONE
                onlineSearchManager.search(link)
                keyboardManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)

            } else {
                Toast.makeText(this, "Vui lòng nhập dữ kiện", Toast.LENGTH_LONG).show()
            }

        }

        onl_sch_chem_list_adapter.setOnItemClickListener(object : RecyclerViewSearchCEqAdapter.OnItemClickListener {
            override fun onClick(view: View, equation: ChemicalEquation, position: Int) {
                val intent = Intent(this@OnlineSearchChemicalEquationActivity, ChemicalEquationActivity::class.java)
                intent.putExtra(ChemicalEquationActivity.CHEMICAL_EQUATION, equation)
                startActivity(intent)
            }
        })

        addSearchingEvent()
    }

    fun addSearchingEvent () {

        var onReachASearch : OnReachASearch = {
            if (it != null) {
                if (isNewSearch) {
                    onl_sch_chem_list_adapter.list = ArrayList()
                    isNewSearch = false
                    hasData = true
                }
                onl_sch_chem_list_adapter.list.addAll(it)
            }
        }

        var onSearchSuccess : () -> Unit = {
            if (!hasData) {
                onl_sch_chem_list_adapter.list = ArrayList()
                no_result_found.visibility = VISIBLE
            }
            onl_sch_chem_list_adapter.notifyDataSetChanged()
            onl_sch_progress_bar.visibility = GONE

        }

        onlineSearchManager = OnlineSearchChemEquaManager(onReachASearch, onSearchSuccess)

    }

    fun initViewModel () {
        viewModel = getViewModel()

    }


    fun initRecyclerView () {
        onl_sch_chem_list_adapter = RecyclerViewSearchCEqAdapter(this, ArrayList())
        onl_sch_chem_list_adapter.adaptFor(onl_sch_eq_rcv_chemical_equations)
    }
}
