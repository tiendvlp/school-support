package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IUploadImageStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent
import java.io.ByteArrayOutputStream
import kotlin.random.Random

class UploadImageStories : IUploadImageStories {
    private var mFirebaseStorage : FirebaseStorage
    constructor() {
        mFirebaseStorage = FirebaseStorage.getInstance()
    }
    override fun upload(storyId:String,bitmap: Bitmap, listener: UploadAvatarEvent, imgCount:Int) {
        val imgName = "" + Random.nextInt(1, 90000) + imgCount
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        mFirebaseStorage.reference.child("Stories/$storyId/Content/${imgName}")
            .putBytes(data)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    mFirebaseStorage.reference.child("Stories/$storyId/Content/${imgName}")
                        .downloadUrl
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                listener.onUploadSuccess(it.result.toString()!!)
                            } else {
                                listener.onUploadFailed(it.exception!!.message!!)
                            }
                        }
                } else {
                        listener.onUploadFailed(it.exception!!.message!!)
                }
            }

    }

}