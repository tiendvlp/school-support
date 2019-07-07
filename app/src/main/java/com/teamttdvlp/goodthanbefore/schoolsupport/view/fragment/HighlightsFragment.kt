package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R

class HighlightsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_highlights, container, false)
    }

    companion object {
        private val mInstance : HighlightsFragment = HighlightsFragment()
        fun getInstance () : HighlightsFragment {
            return mInstance
        }}
}
