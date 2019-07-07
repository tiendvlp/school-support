package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.SearchByPeopleFragment
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.SearchByStoriesFragment
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.SearchByTagsFragment

class SearchDirectoryViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchByStoriesFragment()
            1 -> SearchByPeopleFragment()
            else -> SearchByTagsFragment()
        }
    }

    override fun getCount(): Int = 3
}