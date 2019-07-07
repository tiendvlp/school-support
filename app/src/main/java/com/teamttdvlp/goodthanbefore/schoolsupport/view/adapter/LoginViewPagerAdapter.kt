package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.LoginFragment
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.SignUpFragment

class LoginViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return LoginFragment.getInstance()
            } else {
                return SignUpFragment.getInstance()
            }
    }

    override fun getCount(): Int {
        return 2
    }

}