package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IGlobalStories
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IHotStories
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserBookMark
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserHistoryStories
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserLikedStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserBookMark
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserHistoryStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserLikedStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserCompactStory
import java.lang.Exception

class UserStoriesManger {
    private var mGlobalStories : IGlobalStories
    private var mUserLikedStories : IUserLikedStories
    private var mUserHistoryStories: IUserHistoryStories
    private var mUserBookmarkStories : IUserBookMark
    private var mHotStories : IHotStories
    var onGetLikedStoriesListener:GetMultipleStories? = null
    var onGetHistorialStoriesListener : GetMultipleStories? = null
    var onGetBookmarkStoriesListener : GetMultipleStories? = null
    var onGetHotStoriesListener:GetMultipleStories? = null
    constructor() {
        mHotStories = HotStories()
        mUserBookmarkStories = UserBookMark()
        mUserHistoryStories = UserHistoryStories()
        mGlobalStories = GlobalStories()
        mUserLikedStories = UserLikedStories()
    }

    fun getHotStories (topics:ArrayList<String>, fromThree: Long, fromFive: Long, fromSeven: Long) {
        mHotStories.getHotStoriesByTopic(topics, fromThree,fromFive,fromSeven, onGetHotStoriesListener)
    }

    fun getUserLikedStories (userId:String, count:Int) {
        mUserLikedStories.setCount(count)
        mUserLikedStories.get(userId, object:GetUserCompactStory{
            override fun onGetUserCompactStoryEventSuccess(stories: ArrayList<CompactStory>) {
                var storiesId:ArrayList<String> = ArrayList()
                for (story in stories) {
                    storiesId.add(story.Id)
                }
                mGlobalStories.getMultipleStories(storiesId, object : GetMultipleStories{
                    override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
                        onGetLikedStoriesListener?.onGetMultipleStoriesSuccess(result)
                    }

                    override fun onGetMultipleStoriesFailed() {
                        onGetLikedStoriesListener?.onGetMultipleStoriesFailed()
                    }
                })
            }

            override fun onGetUserCompactStoryEventFailed(e: Exception?) {
                onGetLikedStoriesListener?.onGetMultipleStoriesFailed()
            }

        })
    }

    fun getUserBookmarkStories (userId:String, count:Int) {
        mUserBookmarkStories.setCount(count)
        mUserBookmarkStories.get(userId, object:GetUserCompactStory{
            override fun onGetUserCompactStoryEventFailed(e: Exception?) {
                    onGetBookmarkStoriesListener?.onGetMultipleStoriesFailed()
            }

            override fun onGetUserCompactStoryEventSuccess(stories: ArrayList<CompactStory>) {
                var storiesId:ArrayList<String> = ArrayList()
                for (story in stories) {
                    storiesId.add(story.Id)
                }
                mGlobalStories.getMultipleStories(storiesId, object : GetMultipleStories{
                    override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
                        onGetBookmarkStoriesListener?.onGetMultipleStoriesSuccess(result)
                    }

                    override fun onGetMultipleStoriesFailed() {
                        onGetBookmarkStoriesListener?.onGetMultipleStoriesFailed()
                    }
                })
            }})}

    fun getUserHistorialStories (userId: String, count: Int) {
        mUserHistoryStories.setCount(count)
        mUserHistoryStories.get(userId, object : GetUserCompactStory {
            override fun onGetUserCompactStoryEventSuccess(stories: ArrayList<CompactStory>) {
                var storiesId: ArrayList<String> = ArrayList()
                for (story in stories) {
                    storiesId.add(story.Id)
                }
                mGlobalStories.getMultipleStories(storiesId, object : GetMultipleStories {
                    override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
                        onGetHistorialStoriesListener?.onGetMultipleStoriesSuccess(result)
                    }

                    override fun onGetMultipleStoriesFailed() {
                        onGetHistorialStoriesListener?.onGetMultipleStoriesFailed()
                    }
                })
            }

            override fun onGetUserCompactStoryEventFailed(e: Exception?) {
                onGetHistorialStoriesListener?.onGetMultipleStoriesFailed()
            }
        })
    }
    }