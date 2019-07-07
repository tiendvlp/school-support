package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories

import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.SearchStoriesByTitle
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SearchStoriesEvent

/**
 * @see SearchStoriesByTitle
 */
interface ISearchStoryByTitle {

    fun search (title: String, callBack : SearchStoriesEvent)

}

