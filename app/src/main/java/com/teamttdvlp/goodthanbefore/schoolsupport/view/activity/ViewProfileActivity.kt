package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ViewProfileActivityBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.UserProfilePagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.ViewProfileActivityViewModel
import kotlinx.android.synthetic.main.activity_view_profile.*

class ViewProfileActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var mBinding: ViewProfileActivityBinding
    private lateinit var mViewModel : ViewProfileActivityViewModel
    private lateinit var mAdapter : UserProfilePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_profile)
        setup()
        addControls()
        addEvents()
    }

    override fun onResume() {
        super.onResume()
        if (CurrentUser.bitmapUserAvatar != null)
        img_avatar.setImageBitmap(CurrentUser.bitmapUserAvatar)
        txt_user_name.text = CurrentUser.currentUser?.DisplayName
        txt_about.text = CurrentUser.currentUser?.About
    }

    private fun setup() {
        mViewModel = getViewModel()
        mBinding.txtUserName.text = CurrentUser.currentUser?.DisplayName
        if (CurrentUser.currentUser?.Avatar != null)
        Picasso.get().load(CurrentUser.currentUser?.Avatar).into(mBinding.imgAvatar)
    }

    private fun addEvents() {
        mBinding.vpgProfile.addOnPageChangeListener(this)
        mBinding.tabLayout.setOnCheckedChangeListener(this)
        mBinding.btnEdit.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> mBinding.btnLastest.isChecked = true
            1 -> mBinding.btnClaps.isChecked = true
            2 -> mBinding.btnHighlights.isChecked = true
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            mBinding.btnLastest.id -> mBinding.vpgProfile.setCurrentItem(0, true)
            mBinding.btnClaps.id -> mBinding.vpgProfile.setCurrentItem(1, true)
            mBinding.btnHighlights.id -> mBinding.vpgProfile.setCurrentItem(2, true)
        }
    }

    private fun addControls() {
        mAdapter = UserProfilePagerAdapter(supportFragmentManager)
        mBinding.vpgProfile.adapter = mAdapter
    }
}
