package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserCompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.NewHistorialStoryEvent
import io.reactivex.Single

interface IUserReadHistoryDb {
    fun get(userId:String, listener: GetUserCompactStory) : Single<CompactStory>
    fun newHistorialStory (storyId:String, listener: NewHistorialStoryEvent)
}