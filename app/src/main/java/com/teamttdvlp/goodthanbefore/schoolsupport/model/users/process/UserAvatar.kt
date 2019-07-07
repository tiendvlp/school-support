package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserAvatar
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent

const val ONE_MEGABYTE = 1024 * 1024L

class UserAvatar : IUserAvatar {
    private var mStorageRef = FirebaseStorage.getInstance()

    override fun uploadAvatar(user : User, imageByteArray: ByteArray, callback : UploadAvatarEvent) {
        mStorageRef.getReference("Users/${user.Id}/Avatar.png")
            .putBytes(imageByteArray).addOnSuccessListener {

                mStorageRef.getReference("Users/${user.Id}/Avatar.png")
                    .downloadUrl.addOnCompleteListener{
                        callback.onUploadSuccess("" + it.result)
                        Log.e("it","a: " + it.result.toString())
                }

            }
            .addOnCanceledListener {
                callback.onUploadFailed("Error while uploading")
            }

    }


    override fun getAvatar(user: User) {
        mStorageRef.getReference("Users/${user.Id}/Avatar.png")
            .getBytes(ONE_MEGABYTE)
            .addOnSuccessListener {

            }
    }



}