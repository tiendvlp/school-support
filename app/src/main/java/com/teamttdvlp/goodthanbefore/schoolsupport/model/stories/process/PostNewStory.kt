package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IPostNewStory
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.DateSupport
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.PostNewStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent
import java.io.ByteArrayOutputStream
import java.lang.Exception

class PostNewStory : IPostNewStory {
    private var mFirebaseStorage: FirebaseStorage
    private var mFirestore : FirebaseFirestore

    constructor() {
        mFirebaseStorage = FirebaseStorage.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
    }

    override fun getStoryId () :String {
        var storyId = mFirestore.collection("Stories").document().id
        return storyId
    }

    val ONE_DAY_IN_MILIS = 86_400_000
    fun createCycle (startTime:Long) : HashMap<String, ArrayList<String>> {
        var result : HashMap<String, ArrayList<String>> = HashMap()
        var sevenCycle : ArrayList<String> = ArrayList()
        var fiveCycle : ArrayList<String> = ArrayList()
        var threeCycle : ArrayList<String> = ArrayList()
        for (i in 0..2) {
            threeCycle.add(DateSupport.getDateByTimeMillis(startTime + i * ONE_DAY_IN_MILIS))
        }

        for (i in 0..4) {
            fiveCycle.add(DateSupport.getDateByTimeMillis(startTime + i * ONE_DAY_IN_MILIS))
        }

        for (i in 0..6) {
            sevenCycle.add(DateSupport.getDateByTimeMillis(startTime + i * ONE_DAY_IN_MILIS))
        }
        result["three"] = threeCycle
        result["five"] = fiveCycle
        result["seven"] = sevenCycle
        return result
    }

    override fun postStory(id : String?,story: Stories, storyAvatar:Bitmap, listener : PostNewStoryEvent) {
        val storyId : String
        if (id !=null) {
            storyId = id!!
        } else {
            storyId = getStoryId()
        }
        var firstCycle = createCycle(System.currentTimeMillis())
        story.Id = storyId
        story.ThreeHotDayLifeCycle = firstCycle["three"]!!
        story.FiveHotDayLifeCycle = firstCycle["five"]!!
        story.SevenHotDayLifeCycle = firstCycle["seven"]!!
        story.splitTitle.clear()
        story.splitTitle.putAll(splitTitle(story.Title))
        Log.d("StoryId", story.Id)
        postStoryAvatar(storyAvatar, story.Id,object : UploadAvatarEvent {
            override fun onUploadSuccess(downloadUri: String) {
                story.Avatar = downloadUri
                mFirestore.collection("Stories").document(storyId).set(story)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                                    if (it.isSuccessful) {
                                        listener.onPostNewStorySuccess()
                                    } else {
                                        listener.onPostNewStoryFailed(it.exception)
                                    }
                        } else {
                            listener.onPostNewStoryFailed(it.exception)
                        }
                    }
            }

            override fun onUploadFailed(message: String) {
                listener.onPostNewStoryFailed(Exception(message))
            }

        })
    }

    private fun splitTitle (title:String) : HashMap<String, Boolean> {
        var titleParts = title.toLowerCase().split(" ")
        var result : HashMap<String, Boolean> = HashMap()
        for (part in titleParts) {
            result[part] = true
        }
        return result
    }

    override fun postStoryAvatar(bitmap: Bitmap, storyId:String, listener : UploadAvatarEvent) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val data = baos.toByteArray()
        mFirebaseStorage.getReference("Stories/${storyId}/avatar.png")
            .putBytes(data)
            .addOnSuccessListener {
                if (it.error == null) {
                    mFirebaseStorage.getReference("Stories/${storyId}/avatar.png")
                        .downloadUrl
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                listener.onUploadSuccess(downloadUri = it.result.toString())
                            } else{
                                listener.onUploadFailed(it.exception!!.message!!)
                            }
                        }
                } else {
                    Log.d("PostUploadFailed", it.error?.message)
                    listener.onUploadFailed(it.error?.message!!)
                }
            }
        }
}