package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import android.util.Log
import com.google.firebase.functions.FirebaseFunctions
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IHotStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.DateSupport
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import org.json.JSONArray
import org.json.JSONObject

class HotStories : IHotStories {
    private var minximumThreeCheckPoint:Long = Long.MAX_VALUE
    private var minximumFiveCheckPoint:Long = Long.MAX_VALUE
    private var minximumSevenCheckPoint:Long = Long.MAX_VALUE

    override fun getHotStoriesByTopic(
        topics: ArrayList<String>, fromThree: Long,
        fromFive: Long,
        fromSeven: Long, listener: GetMultipleStories?) {
        Log.d("UserInterest", topics.toString())
        val data : HashMap<String, Any> = HashMap()
        data["Interest"] =  topics
        data["FromThree"] = minximumThreeCheckPoint
        data["FromFive"] = minximumFiveCheckPoint
        data["FromSeven"] = minximumSevenCheckPoint
        data["Date"]= DateSupport.getCurrentDate()
        Log.d("currentDate", DateSupport.getCurrentDate())
        FirebaseFunctions.getInstance().getHttpsCallable("getHotStoriesByTopic")
            .call(data)
            .addOnCompleteListener {
                if (it.isSuccessful && it.result?.data !=null) {
                    val result : ArrayList<Stories> = ArrayList()
                    val jsArr : JSONArray = JSONArray(it.result!!.data.toString())
                    for (i in 0..jsArr.length()-1) {
                        val storiesJS:JSONObject = jsArr[i] as JSONObject
                        val mStories
                                = Stories.fromJSONObject(storiesJS)
                        result.add(mStories)
                        if (mStories.ThreeHotDayCycle < minximumThreeCheckPoint) {
                            minximumThreeCheckPoint = mStories.ThreeHotDayCycle
                        }

                        if (mStories.FiveHotDayCycle < minximumFiveCheckPoint) {
                            minximumFiveCheckPoint = mStories.FiveHotDayCycle
                        }

                        if (mStories.SevenHotDayCycle < minximumSevenCheckPoint) {
                            minximumSevenCheckPoint = mStories.SevenHotDayCycle
                        }
                    }
                    listener?.onGetMultipleStoriesSuccess(result)
                } else {
                    listener?.onGetMultipleStoriesFailed()
                }
            }
    }
}