package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.SearchStoriesByTitle
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SearchStoriesEvent

class SearchStoriesManager {
    val searchByTitle : SearchStoriesByTitle = SearchStoriesByTitle()
    fun searchStoriesByTitle (title : String, callBack : SearchStoriesEvent) {
        searchByTitle.search(title, callBack)
    }
}