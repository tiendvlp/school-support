package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_profile.*
import android.content.Intent
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.activity.EditProfileActivityBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UpdateInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.EditProfileActivityViewModel


class EditProfileActivity : AppCompatActivity(), UpdateInfoEvent {

    private lateinit var mViewModel : EditProfileActivityViewModel
    private lateinit var mBinding : EditProfileActivityBinding
    lateinit var currentUser : User

    var currentAvatar : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        currentUser = CurrentUser.currentUser!!
        currentAvatar = CurrentUser.bitmapUserAvatar
        if (currentAvatar != null) img_avatar.setImageBitmap(currentAvatar)
        mViewModel = getViewModel()
        addControls()
        addEvents ()
    }

    private fun addControls() {
        edt_user_name.setText(currentUser.DisplayName)
        edt_about.setText(currentUser.About)
    }

    private fun addEvents() {
        btn_close.setOnClickListener {
            finish()
        }

        btn_save.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            save()
        }

        btn_edit_profile_picture.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                img_avatar.setImageURI(resultUri)
                currentAvatar = (img_avatar.drawable as BitmapDrawable).bitmap
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Log.e("ERROR: ", "EditProfileActivity.kt \n onActivityResult \n $error")
            }
        }
    }

    // Update User info
    override fun onUpdateSuccess() {
        progress_bar.visibility = View.GONE
        finish()
        Log.e("Update:", "Success")
    }

    override fun onUpdateFailed() {
        Toast.makeText(this, "Đã xảy ra lỗi", Toast.LENGTH_LONG).show()
        Log.e("Update:", "Failed")
    }

    private fun save () {
        if (currentAvatar != CurrentUser.bitmapUserAvatar) {
            mViewModel.uploadAvatar(currentUser, currentAvatar!!, object : UploadAvatarEvent {
                override fun onUploadSuccess(downloadUri: String) {
                    Log.e("Upload:", "Success. Uri: $downloadUri" )
                    currentUser.DisplayName = edt_user_name.text.toString()
                    currentUser.About = edt_about.text.toString()
                    currentUser.Avatar = downloadUri
                    CurrentUser.bitmapUserAvatar = currentAvatar
                    CurrentUser.currentUser = currentUser
                    mViewModel.updateUserInfo(currentUser, this@EditProfileActivity)
                }

                override fun onUploadFailed(message : String) {
                    Toast.makeText(this@EditProfileActivity, "Đã xảy ra lỗi", Toast.LENGTH_LONG).show()
                    Log.e("Upload:", "Failed. Error: $message")
                }
            })
        } else {
            currentUser.DisplayName = edt_user_name.text.toString()
            currentUser.About = edt_about.text.toString()
            CurrentUser.currentUser = currentUser
            mViewModel.updateUserInfo(currentUser, this@EditProfileActivity)
        }

    }

}
