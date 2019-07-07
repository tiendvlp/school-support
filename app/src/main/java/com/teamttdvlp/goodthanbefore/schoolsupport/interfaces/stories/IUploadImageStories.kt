package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories

import android.graphics.Bitmap
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent

interface IUploadImageStories {
    fun upload (storyId:String, bitmap:Bitmap, listener : UploadAvatarEvent, imgCount:Int)
}