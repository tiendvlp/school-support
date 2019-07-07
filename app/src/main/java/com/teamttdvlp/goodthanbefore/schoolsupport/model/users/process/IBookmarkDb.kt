package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.DeleteBookmarkEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.NewBookmarkEvent
import io.reactivex.Single

interface IBookmarkDb {
    fun getBookmark (userId:String) : Single<CompactStory>
    fun addNewBookmark (storyId:String, listener: NewBookmarkEvent)
    fun deleteBookmark (storyId:String, listener: DeleteBookmarkEvent)
    fun isExist (storyId: String) : Single<Boolean>
}