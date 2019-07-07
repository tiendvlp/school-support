package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.PostNewStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.PostNewStoryEvent
import java.text.SimpleDateFormat
import java.util.*

class SubmitStoryFragmentViewModel : ViewModel {
    var currentStory : MutableLiveData<Stories> = MutableLiveData()
    private var mPostStory : PostNewStory

    fun getCurrentDate () : String {
        var format = SimpleDateFormat("dd/MM/yyyy")
        var date = Date()
        return format.format(date)
    }

    constructor() {
        mPostStory = PostNewStory()
        currentStory.value = Stories()
    }

    fun postStory(listener:PostNewStoryEvent, storyAvatar:Bitmap) {
        mPostStory.postStory(null, currentStory.value!!,storyAvatar, listener)
    }

}