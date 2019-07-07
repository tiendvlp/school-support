package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserHistoryStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserCompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.NewHistorialStoryEvent

class UserHistoryStories : IUserHistoryStories {

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
            .collection("HistorialStories")
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
    override fun newHistorialStory(storyId: String, listener: NewHistorialStoryEvent) {
        val compactStory : CompactStory = CompactStory()
        compactStory.Id = storyId
        compactStory.Time = System.currentTimeMillis()
        mFirestore.collection("Users")
            .document(CurrentUser.currentUser!!.Id)
            .collection("HistorialStories")
            .document(storyId)
            .set(compactStory)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onNewHistorialStorySuccess()
                } else {
                    listener.onNewHistorialStoryFailed(it.exception)
                }
            }
    }

}