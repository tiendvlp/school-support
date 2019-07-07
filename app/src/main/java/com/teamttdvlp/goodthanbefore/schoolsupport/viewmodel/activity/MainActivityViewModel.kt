package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser

class MainActivityViewModel : ViewModel() {

    var mStorageRef = FirebaseStorage.getInstance()

    fun setUpCurrentUser () {
        mStorageRef.getReference("Users/${CurrentUser.currentUser!!.Id}/Avatar.png").getBytes(1024 * 1024)
            .addOnSuccessListener {
                var bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                CurrentUser.bitmapUserAvatar = bitmap
            }
            .addOnFailureListener {
                Log.e("FAILED", "FAILEDDDDDDDDDDD")
            }
    }
}