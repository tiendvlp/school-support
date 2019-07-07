package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.SearchStoriesManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SearchStoriesEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SearchActivity

/**
 * @see SearchActivity
 */
class SearchActivityViewModel : ViewModel () {

    val searchManager = SearchStoriesManager()

    var resultStories = MutableLiveData<ArrayList<Stories>>()

    fun searchStoriesByTitle (keyword : String, callBack : SearchStoriesEvent) {
        searchManager.searchStoriesByTitle (keyword, callBack)
    }

}