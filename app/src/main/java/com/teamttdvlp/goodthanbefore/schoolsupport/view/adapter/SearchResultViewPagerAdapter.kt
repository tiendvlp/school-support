package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.*

class SearchResultViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HistoryFragment()
            else -> TopFragment()
        }
    }

    override fun getCount(): Int = 2
}