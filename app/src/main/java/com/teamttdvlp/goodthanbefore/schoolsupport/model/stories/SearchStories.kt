package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.ISearchStoryByTitle
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SearchStoriesEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError

class SearchStoriesByTitle : ISearchStoryByTitle {

    private val mFirestore: FirebaseFirestore

    constructor() {
        mFirestore= FirebaseFirestore.getInstance()
    }

    override fun search (title : String, callBack : SearchStoriesEvent) {
        var col = mFirestore.collection("Stories")
        var titleParts = splitTitle(title)
        var queries : Query? = null
        for (part in titleParts) {
            if (queries == null) {
                queries = col.whereEqualTo("splitTitle.${part}", true)
            } else {
                queries = queries.whereEqualTo(part, true)
            }

        }

        queries!!.get().addOnCompleteListener {
            if (it.isSuccessful) {
                var docs = it.result!!.documents
                var result = ArrayList<Stories>()
                for (doc in docs) {
                    result.add(doc.toObject(Stories::class.java) as Stories)
                }
                callBack.onSearchStoriesSuccess(result)
            } else {
                callBack.onSearchStoriesFailed(it.exception)
            }
        }
    }

    fun splitTitle (title : String) : List<String> {
        return title.toLowerCase().split(' ')
    }

}