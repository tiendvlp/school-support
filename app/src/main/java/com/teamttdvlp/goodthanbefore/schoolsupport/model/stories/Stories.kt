package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories

import android.util.Log
import org.json.JSONObject
import java.io.Serializable

class Stories : Serializable {
    // spawn
    var Avatar : String =""
    var Content : String =""
    // spawn
    var Id : String =""
    // spawn
    var Tag : String = ""
    // spawn từ tháng 1 đến tháng 3
    var PostedTime : Long = 0
    // spawn
    var Topic : String =""
    // spawn
    var Claps : Int = 0
    var Review : String=""
    // spawn
    var Views : Int = 0
    // spawn: điểm này spawn giá trị từ 0 -> 100
    var Reputation : Int = 0
    // spawn: Đây là số lượng điểm Hot trong 3 ngày, chỉ cần spawn ngẫu nhiên là được từ 200 -> 1000
    var ThreeHotDayCycle : Long = 0
    // spawn: Đây là số lượng điểm Hot trong 5 ngày, chỉ cần spawn ngẫu nhiên là được từ 200 -> 1000
    var FiveHotDayCycle : Long= 0
    // spawn: Đây là số lượng điểm Hot trong 7 ngày, chỉ cần spawn ngẫu nhiên là được từ 200 -> 1000
    var SevenHotDayCycle : Long= 0
    // spawn
    var Title : String = ""
    // spawn
    var Author :String = ""
    var AuthorDisplayName=""
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var ThreeHotDayLifeCycle : ArrayList<String> = ArrayList()
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var FiveHotDayLifeCycle : ArrayList<String> = ArrayList()
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var SevenHotDayLifeCycle : ArrayList<String> = ArrayList()
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var ReputationLifeCycle : ArrayList<String> = ArrayList()
    var splitTitle : HashMap<String, Boolean> = HashMap()
    // spawn: random từ 1 đến 4
    // nếu là 1 -> isRep=true, is Hot=false
    // nếu là 2 -> isRep=true, is Hot = true
    // nếu là 3 -> isRep = false, isHot = true
    //nêu slà 4 -> isRep = false, isHot=false
    var isRep = false
    var isHot = false

    companion object {
        fun fromJSONObject (js:JSONObject) : Stories {
            var result = Stories()
            result.Avatar = js.getString("avatar")
            result.Id = js.getString("id")
            result.Title = js.getString("title")
            result.Topic = js.getString("topic")
//            result.Tag = js.getString("tag")
            result.Content = js.getString("content")
            result.Views = js.getInt("views")
            result.Reputation = js.getInt("reputation")
            result.ThreeHotDayCycle = js.getLong("threeHotDayCycle")
            result.SevenHotDayCycle= js.getLong("sevenHotDayCycle")
            result.FiveHotDayCycle= js.getLong("fiveHotDayCycle")
            Log.d("parserJS",js.getLong("threeHotDayCycle").toString())
            Log.d("parserJS",js.getLong("sevenHotDayCycle").toString())
            Log.d("parserJS",js.getLong("fiveHotDayCycle").toString())
            result.Claps = js.getInt("claps")
            result.Author = js.getString("author")
            result.PostedTime = js.getLong("postedTime")
            result.AuthorDisplayName = js.getString("authorDisplayName")
            val threeHotDayLifeCycleJson = js.getJSONArray("threeHotDayLifeCycle")
            val fiveHotDayLifeCycleJson = js.getJSONArray("fiveHotDayLifeCycle")
            val sevenHotDayLifeCycleJson = js.getJSONArray("sevenHotDayLifeCycle")
            val reputationLifeCycleJson = js.getJSONArray("reputationLifeCycle")

            for (i in 0..threeHotDayLifeCycleJson.length()-1)  {
                result.ThreeHotDayLifeCycle.add(threeHotDayLifeCycleJson[i].toString())
            }

            for (i in 0..sevenHotDayLifeCycleJson.length()-1)  {
                result.SevenHotDayLifeCycle.add(sevenHotDayLifeCycleJson[i].toString())
            }

            for (i in 0..fiveHotDayLifeCycleJson.length()-1)  {
                result.FiveHotDayLifeCycle.add(fiveHotDayLifeCycleJson[i].toString())
            }

            for (i in 0..reputationLifeCycleJson.length()-1)  {
                result.ReputationLifeCycle.add(reputationLifeCycleJson[i].toString())
            }

            return result
        }
    }
}