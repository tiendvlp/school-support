package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetStory

interface IGlobalStories {
    fun getStory(storyId:String, listener:GetStory)
    fun getMultipleStories (storyId: ArrayList<String>, listener: GetMultipleStories)
}