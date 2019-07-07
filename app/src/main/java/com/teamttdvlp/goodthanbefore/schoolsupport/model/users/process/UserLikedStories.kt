package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserLikedStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.DeleteLikeStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserCompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.IsStoryExistInLikeListEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.NewLikeStoryEvent

class UserLikedStories : IUserLikedStories {
    private var count = 0
    private var currentItem : CompactStory
    private var mFirestore : FirebaseFirestore

    constructor() {
        mFirestore = FirebaseFirestore.getInstance()
        currentItem = CompactStory()
        currentItem.Time = Long.MAX_VALUE
    }
    override fun setCount(count: Int) {
        this.count = count
    }

    override fun get(userId: String, listener: GetUserCompactStory) {
        mFirestore.collection("Users")
            .document(userId)
            .collection("LikedStories")
            .orderBy("time", Query.Direction.DESCENDING)
            .whereLessThan("time", currentItem.Time)
            .limit(this.count.toLong())
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    var result:ArrayList<CompactStory> = ArrayList()
                    for (doc in it.result!!.documents) {
                        var obj : CompactStory? =  doc.toObject(CompactStory::class.java)
                        if (obj != null) {
                            result.add(doc.toObject(CompactStory::class.java)!!)
                        }
                    }
                    if (result.size > 0) {
                        this.currentItem = result[result.size-1]
                    }
                    listener.onGetUserCompactStoryEventSuccess(result)
                } else {
                    listener.onGetUserCompactStoryEventFailed(it.exception)
                }
            }
    }
    override fun newLikedStory(storyId: String, listener: NewLikeStoryEvent) {
        var compactStory:CompactStory = CompactStory()
        compactStory.Time = System.currentTimeMillis()
        compactStory.Id = storyId
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("LikedStories")
            .document(storyId)
            .set(compactStory)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onNewLikeStorySuccess()
                } else {
                    listener.onNewLikeStoryFailed(it.exception)
                }
            }
    }

    override fun deleteLikeStory(storyId: String, listener: DeleteLikeStoryEvent) {
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("LikedStories")
            .document(storyId)
            .delete()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onDeleteLikeStorySuccess()
                } else {
                    listener.onDeleteLikeStoryFailed(it.exception)
                }
            }
    }

    override fun isStoryExist(storyId: String, listener: IsStoryExistInLikeListEvent) {
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("LikedStories")
            .document(storyId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                     Log.d("aaa",it.result!!.exists().toString())
                    listener.onStoryExistInLikeListSuccess(it.result!!.exists())
                } else {
                    listener.onStoryExistInLikeListFailed(it.exception)
                }
            }
    }
}