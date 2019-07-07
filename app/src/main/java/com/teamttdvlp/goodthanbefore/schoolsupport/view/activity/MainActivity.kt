package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    lateinit var mainNavHost : NavController
    lateinit var mViewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
    }

    private fun addControls() {
        mViewModel = getViewModel()
        mainNavHost = Navigation.findNavController(this, R.id.main_nav_host)
        mViewModel.setUpCurrentUser()
    }

    private fun addEvents() {
        rbtn_global.setOnCheckedChangeListener (this)
        rbtn_local.setOnCheckedChangeListener (this)
        rbtn_tool.setOnCheckedChangeListener (this)
        rbtn_bookmarks.setOnCheckedChangeListener (this)
        rbtn_new_stories.setOnClickListener {
            startActivity(Intent(this, CreateStoryActivity::class.java))
        }
    }

    override fun onCheckedChanged(view: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            val navigatingFragmentId : Int? = when (view?.id) {
                R.id.rbtn_global -> R.id.main_nav_fragment_global
                R.id.rbtn_local -> R.id.main_nav_fragment_local
                R.id.rbtn_tool -> R.id.main_nav_fragment_tool
                R.id.rbtn_bookmarks -> R.id.main_nav_fragment_bookmarks
                else -> null
            }
            if (navigatingFragmentId != null) mainNavHost.navigate(navigatingFragmentId)
        }
    }

}
