package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserAvatar
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.EditProfileActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserAvatar
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UpdateInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import java.io.ByteArrayOutputStream
import java.lang.Exception

/**
 * @see EditProfileActivity
 */
class EditProfileActivityViewModel : ViewModel() {

    var userAvatar : IUserAvatar = UserAvatar()
    var mFirestoreRef = FirebaseFirestore.getInstance()
    var userManager : UserManager = UserManager()

    fun updateUserInfo (user : User, callback: UpdateInfoEvent) {
        userManager.setUserInfoListener = object : WriteInfoEvent {
            override fun onWriteInfoSuccess() {
                callback.onUpdateSuccess()
            }

            override fun onWriteInfoFailed(e: Exception?) {
                callback.onUpdateFailed()
            }
        }
        userManager.writeInfo(user)
    }

    fun uploadAvatar (user : User, image : Bitmap, uploadAvatarEvent: UploadAvatarEvent) {
        userAvatar.uploadAvatar(user, drawableToPNG(image), uploadAvatarEvent)
    }

    private fun drawableToPNG (source : Bitmap) : ByteArray {
        val ouputStream = ByteArrayOutputStream()
        source.compress(Bitmap.CompressFormat.PNG, 100, ouputStream)
        return ouputStream.toByteArray()
    }
}