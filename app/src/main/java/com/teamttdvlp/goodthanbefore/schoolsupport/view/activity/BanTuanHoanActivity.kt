package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.retroschoolsupporttoolmodule.SearchChemicalElementRCVAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.BangTuanHoanActivityViewModel
import kotlinx.android.synthetic.main.activity_bang_tuan_hoan.*

class BanTuanHoanActivity : AppCompatActivity() {

    private lateinit var fadeOut: Animation

    private lateinit var fadeIn: Animation

    lateinit var mViewModel : BangTuanHoanActivityViewModel

    private var rcvSearchList = ArrayList<ChemicalElement>()

    private lateinit var rcvSearchAdapter : SearchChemicalElementRCVAdapter

    private lateinit var BangTuanHoanNav : NavController

    private var isSearchOn = false

    private var isStartAppFocus = true

    private lateinit var inputMethodManager : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bang_tuan_hoan)
        setUpFunctionalSupporter()
        setUpAnimation()
        addControls()
        addEvents()
    }

    private fun setUpFunctionalSupporter () {
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun addControls() {
        BangTuanHoanNav = Navigation.findNavController(this, R.id.bth_bangtuanhoan_nav)
        mViewModel = getViewModel()
        mViewModel.searchCEListData.observe(this, Observer {
            setUpSearchRCV(it)
        })

        // Make keyboard not auto turn on when start activity
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun setUpSearchRCV (data : ArrayList<ChemicalElement>) {
        rcvSearchAdapter = SearchChemicalElementRCVAdapter(this)
        rcvSearchAdapter.adaptFor(lv_search)
        rcvSearchAdapter.setData(data)
        rcvSearchAdapter.observe(edt_search)
        rcvSearchAdapter.setOnItemClickListener (object : SearchChemicalElementRCVAdapter.OnItemClickListener {
            override fun onItemClick(element: ChemicalElement) {
                if ((BangTuanHoanNav.currentDestination != BangTuanHoanNav.graph.findNode(R.id.bth_nav_fragment_watch_ceinfo))) {
                    var bundle = Bundle()
                    bundle.putParcelable("chemical_element", element)
                    BangTuanHoanNav.navigate(R.id.action_bth_nav_fragment_bth_to_watch_ceinfo_fragment, bundle)
                    lv_search.visibility = View.GONE
                } else {
                    // Update element to FragmentWatchCEInfo because it is observing to this
                    // livedata
                    mViewModel.chosenChemicalElement.value = element
                    lv_search.visibility = View.GONE
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                }
            }
        })
    }

    private fun addEvents() {

        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        btn_cancel.setOnClickListener {
            if (lv_search.visibility != View.GONE)
                lv_search.visibility = View.GONE
        }

        edt_search.isFocusableInTouchMode = true

        edt_search.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (lv_search.visibility != View.VISIBLE)
                    lv_search.visibility = View.VISIBLE
                edt_search.showSoftInputOnFocus = true
                if (!edt_search.isFocused) {
                    edt_search.requestFocus()
                    inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
                }
                return@setOnTouchListener true
            }
            false
        }

        btn_search.setOnClickListener {
            if (lv_search.visibility != View.VISIBLE)
                lv_search.visibility = View.VISIBLE
        }
    }

    private fun setUpAnimation () {
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        fadeOut.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                lv_search.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

        fadeIn.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {
                Log.e("WHAT THE FUCK", "CAC")
                lv_search.visibility = View.VISIBLE
            }

        })
    }

}
