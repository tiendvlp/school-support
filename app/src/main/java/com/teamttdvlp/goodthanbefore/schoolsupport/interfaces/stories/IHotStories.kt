package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories

interface IHotStories {
     fun getHotStoriesByTopic (
          topics: ArrayList<String>, fromThree: Long,
          fromFive: Long,
          fromSeven: Long,
          listener: GetMultipleStories?)
}