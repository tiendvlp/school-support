package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories

import android.graphics.Bitmap
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.PostNewStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent

interface IPostNewStory {
    fun postStory (id : String?,story:Stories, storyAvatar:Bitmap,listener : PostNewStoryEvent)
    fun postStoryAvatar (bitmap:Bitmap, storyId:String, listener : UploadAvatarEvent)
    fun getStoryId () :String
}