package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.*

interface IUserBookMark  {
    fun setCount(count:Int)
    fun get(userId:String, listener: GetUserCompactStory)
    fun newBookmark (storyId:String, listener:NewBookmarkEvent)
    fun deleteBookmark (storyId:String, listener: DeleteBookmarkEvent)
    fun isStoryExist (storyId: String, listener: IsStoryExistInBookmarkListEvent)

}