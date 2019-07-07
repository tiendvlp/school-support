package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.activity.UserActivityBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.UserActivityViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var mBinding : UserActivityBinding
    private lateinit var mViewModel: UserActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        addControls()
        addEvents()
        setUp()
    }

    private fun addControls() {
        mViewModel = getViewModel ()
    }

    override fun onResume() {
        super.onResume()
        if (CurrentUser.bitmapUserAvatar != null)
        img_avatar.setImageBitmap(CurrentUser.bitmapUserAvatar)
        txt_user_name.text = CurrentUser.currentUser?.DisplayName
    }

    private fun addEvents() {
        mBinding.btnViewProfile.setOnClickListener {
            val intent = Intent(this, ViewProfileActivity::class.java)
            intent.putExtra("User", CurrentUser.currentUser)
            startActivity(intent)
        }

        mBinding.btnCustomizeYourInterest.setOnClickListener {
            val intent = Intent(this, InterestActivity::class.java)

            intent.putExtra("User",CurrentUser.currentUser)
            startActivity(intent)
        }
        mBinding.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var i = Intent(this, LoginActivity::class.java)
            finishAffinity()
            startActivity(i)
            CurrentUser.currentUser = null
        }
    }

    private fun setUp() {
        mBinding.txtUserName.text =CurrentUser.currentUser!!.DisplayName
    }

}
