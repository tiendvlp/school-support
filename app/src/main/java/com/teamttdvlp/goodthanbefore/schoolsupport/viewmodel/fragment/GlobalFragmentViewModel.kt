package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories

class GlobalFragmentViewModel : ViewModel {
    private var mUserStoriesManger : UserStoriesManger
    var storyData:ArrayList<Stories> = ArrayList()

    constructor() {
        mUserStoriesManger = UserStoriesManger()
    }

    fun loadHotStories (topics:ArrayList<String>, fromThree: Long, fromFive: Long, fromSeven: Long, listener:GetMultipleStories) {
        mUserStoriesManger.onGetHotStoriesListener = listener
        mUserStoriesManger.getHotStories(topics, fromThree,fromFive,fromSeven)
    }

    fun removeDuplicateItem () {
        var unique : ArrayList<Stories> = ArrayList()
        var isUnique = false
        for (i in 0..storyData.size-1) {
            isUnique = true
            for (i2 in i+1..storyData.size-1) {
                isUnique = !(storyData[i].Id == storyData[i2].Id)
                if (!isUnique) {
                    break
                }
            }
            if(isUnique){
                unique.add(storyData[i])
            }
        }
        Log.d("unique", unique.toString())
        storyData.clear()
        storyData.addAll(unique)
    }

}