package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.DeleteLikeStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserCompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.IsStoryExistInLikeListEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.NewLikeStoryEvent
import io.reactivex.Single

interface IUserLikedStoryDb {
    fun getLikedStory (userId:String) : Single<CompactStory>
    fun addNewLikedStory (storyId:String, listener: NewLikeStoryEvent)
    fun deleteLikedStory (storyId: String, listener: DeleteLikeStoryEvent)
    fun isStoryLiked (storyId: String) : Single<Boolean>
}