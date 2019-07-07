package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IGlobalStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetStory
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.Result
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.resume

class GlobalStories : IGlobalStories{
    override fun getStory(storyId: String, listener: GetStory) {
        FirebaseFirestore.getInstance().collection("Stories")
            .document(storyId)
            .get()
            .addOnCompleteListener({
                if (it.isSuccessful) {
                    val stories : Stories? = it.result?.toObject(Stories::class.java)
                    if (stories != null) {
                        listener.onGetStorySuccess(stories)
                    }
                } else {
                    listener.onGetStoryFailed(it.exception)
                }
            })
    }

    override fun getMultipleStories(storyId: ArrayList<String>, listener: GetMultipleStories) {
        GlobalScope.launch {
            val job : ArrayList<Deferred<Result<Stories>>> = ArrayList()
            var failedCount = 0
            for (i in storyId) {
                job.add(async { supportGetStories(i) })
            }
            val result:ArrayList<Stories> = ArrayList()
            for (job in job) {
                var a = job.await().result
                if (a!=null) {
                    result.add(a)
                }
            }
            GlobalScope.launch(Dispatchers.Main) {
            if (failedCount == storyId.size) {
                listener.onGetMultipleStoriesFailed()
            } else {
                    listener.onGetMultipleStoriesSuccess(result)
                }
            }
        }}

    private suspend fun supportGetStories (id:String) : Result<Stories> {
        val mResult:Result<Stories> = Result()
        return suspendCancellableCoroutine { coroutine ->
            getStory(id, object : GetStory {
                override fun onGetStorySuccess(result: Stories) {
                    mResult.success(result)
                    coroutine.resume(mResult)
                }

                override fun onGetStoryFailed(e: Exception?) {
                    mResult.failed(e)
                    coroutine.resume(mResult)
                }

            })
        }
    }

}