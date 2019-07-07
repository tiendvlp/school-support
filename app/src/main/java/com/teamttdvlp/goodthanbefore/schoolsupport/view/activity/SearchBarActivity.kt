package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import kotlinx.android.synthetic.main.activity_search_bar.*

class SearchBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)
        addEvents()
        val keyboardManager = (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        keyboardManager.showInputMethodPicker()
    }

    private fun addEvents() {

        edt_search.setOnEditorActionListener {
                _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            return@setOnEditorActionListener true
        }

        btn_search.setOnClickListener {
            search()
        }

    }

    private fun search () {
        var keyword = edt_search.text.toString()
        var intent = Intent(this, SearchActivity::class.java).putExtra("Keyword", keyword)
        startActivity(intent)
        finish()
    }
}
