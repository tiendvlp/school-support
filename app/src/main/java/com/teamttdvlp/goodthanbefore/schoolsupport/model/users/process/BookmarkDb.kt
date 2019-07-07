package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.DeleteBookmarkEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.NewBookmarkEvent
import io.reactivex.Single

class BookmarkDb : IBookmarkDb {
    override fun getBookmark(userId: String): Single<CompactStory> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewBookmark(storyId: String, listener: NewBookmarkEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteBookmark(storyId: String, listener: DeleteBookmarkEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExist(storyId: String): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}