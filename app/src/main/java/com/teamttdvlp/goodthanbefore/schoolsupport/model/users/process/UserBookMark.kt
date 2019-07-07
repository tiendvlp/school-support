package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserBookMark
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.*

class UserBookMark : IUserBookMark {

    private var count =  0
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
            .collection("Bookmarks")
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

    override fun newBookmark(storyId: String, listener: NewBookmarkEvent) {
        var currentTime = System.currentTimeMillis()
        var storyCompact : CompactStory =  CompactStory()
        storyCompact.Time = currentTime
        storyCompact.Id = storyId
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("Bookmarks")
            .document(storyId)
            .set(storyCompact)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onNewBoomarkSuccess()
                } else {
                    listener.onNewBookmarkFailed(it.exception)
                }
            }
    }

    override fun deleteBookmark(storyId: String, listener: DeleteBookmarkEvent) {
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("Bookmarks")
            .document(storyId)
            .delete()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onDeleteBookmarkSuccess()
                } else {
                    listener.onDeleteBookmarkFailed(it.exception)
                }
            }
        }

    override fun isStoryExist(storyId: String, listener: IsStoryExistInBookmarkListEvent) {
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("Bookmarks")
            .document(storyId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onStoryExistInBookmarkListSuccess(it.result!!.exists())
                } else {
                    listener.onStoryExistInBookmarkListFailed(it.exception)
                }
            }
    }
}