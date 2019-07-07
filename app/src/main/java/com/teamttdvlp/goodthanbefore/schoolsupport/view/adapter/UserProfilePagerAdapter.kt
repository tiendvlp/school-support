package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.ClapsFragment
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.HighlightsFragment
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.HistoryFragment

class UserProfilePagerAdapter : FragmentPagerAdapter {
    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 ->  {return HistoryFragment.getInstance()}
            1 -> {return ClapsFragment.getInstance()}
            else -> {return HighlightsFragment.getInstance()}
        }
    }

    override fun getCount(): Int {
        return 3
    }

}