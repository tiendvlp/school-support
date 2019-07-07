package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserBookMark
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserHistoryStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserLikedStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.*

class ReadStoriesActivityViewModel : ViewModel  {
    var story : Stories
    private var mUserManager : UserManager
    var isBtnBookmarkProcessing : MutableLiveData<Boolean> = MutableLiveData()
    var previousBtnBookmarkStatus : MutableLiveData<Boolean> = MutableLiveData()
    var isBtnLikeProcessing : MutableLiveData<Boolean> = MutableLiveData()
    var previousBtnLikeStatus : MutableLiveData<Boolean> = MutableLiveData()
    private var mBookmarkManager : UserBookMark
    private var mLikedStoryManager : UserLikedStories
    private var mHistoryManager : UserHistoryStories
    constructor() {
        mLikedStoryManager = UserLikedStories()
        mHistoryManager = UserHistoryStories()
        story = Stories()
        mBookmarkManager = UserBookMark()
        mUserManager = UserManager()
        isBtnBookmarkProcessing.value = false
        previousBtnBookmarkStatus.value = false
        isBtnLikeProcessing.value = false
        previousBtnLikeStatus.value = false
    }

    fun getAuthor (authorId:String, listener : ReadInfoEvent) {
        mUserManager.getUserInfoListener = listener
        mUserManager.getInfo(authorId)
    }

    fun setBookmark (storyId:String, listener:NewBookmarkEvent) {
        mBookmarkManager.newBookmark(storyId, listener)
    }

    fun unSetBookmark (storyId : String, listener: DeleteBookmarkEvent) {
        mBookmarkManager.deleteBookmark(storyId, listener)
    }

    fun setLiked (storyId:String, listener:NewLikeStoryEvent) {
        mLikedStoryManager.newLikedStory(storyId, listener)
    }

    fun unSetLike (storyId : String, listener: DeleteLikeStoryEvent) {
        mLikedStoryManager.deleteLikeStory(storyId, listener)
    }

    fun setHistory (storyId: String, listener: NewHistorialStoryEvent) {
        mHistoryManager.newHistorialStory(storyId, listener)
    }

    fun isStoryExistInLikedList (storyId: String, listener: IsStoryExistInLikeListEvent) {
        mLikedStoryManager.isStoryExist(storyId, listener)
    }

    fun isStoryExistInBookmarkList (storyId: String, listener: IsStoryExistInBookmarkListEvent) {
        mBookmarkManager.isStoryExist(storyId, listener)
    }


}