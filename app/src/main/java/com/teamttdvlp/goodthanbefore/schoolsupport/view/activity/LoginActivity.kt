package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.activity.LoginActivityBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.Responsitory.UserResponsitory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.LoginViewPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.LoginFragment
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, LoginFragment.OnButtonsClickListener {

    private lateinit var mViewModel : LoginActivityViewModel
    private lateinit var mBinding : LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addControls()
        addSetup()
        addEvents()
        UserResponsitory().abc()
    }

    override fun onSignUpButtonClick() {
        pg_login.setCurrentItem(1, true)
    }

    private fun addEvents() {

    }

    private fun addSetup() {

    }


    override fun onConnectionFailed(p0: ConnectionResult) {
        mBinding.setLifecycleOwner (this)
    }

    private fun addControls() {
        mViewModel = getViewModel()
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.pgLogin.adapter = LoginViewPagerAdapter(supportFragmentManager)
    }
}
