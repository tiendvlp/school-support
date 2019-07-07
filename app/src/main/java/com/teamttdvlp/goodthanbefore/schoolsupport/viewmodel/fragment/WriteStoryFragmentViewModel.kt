package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IPostNewStory
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IUploadImageStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.PostNewStory
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UploadImageStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent

class WriteStoryFragmentViewModel : ViewModel {
    private val mUploadImage: IUploadImageStories
    private val mStoriesUpload : IPostNewStory

    constructor() {
        mStoriesUpload = PostNewStory()
        mUploadImage = UploadImageStories()
    }

    fun uploadImage (storyId:String,bitmap:Bitmap, listener:UploadAvatarEvent, imgCount:Int) {
        mUploadImage.upload(storyId, bitmap, listener, imgCount)
    }

    fun getStoryId () : String {
        return mStoriesUpload.getStoryId()
    }

}