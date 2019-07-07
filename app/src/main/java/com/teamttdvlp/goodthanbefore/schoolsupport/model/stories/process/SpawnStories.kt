package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process
import android.util.Log
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import java.lang.IndexOutOfBoundsException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random

class SpawnStories {
    var count : Int = 0

    var currentID = 1000

    val start = 1552867200000 // 18/3/2019
    val end = System.currentTimeMillis() //homnay
    val dateFormat = "dd/MM/YYYY"

    private var tagsEx : ArrayList<String> = arrayListOf("AndroidDevelopment"
        , "IOSUI", "UI"
        , "Math", "Physics"
        , "History", "PhysicalEducation"
        , "SexEducation", "IT"
        , "Literature", "Technology"
        , "Biology", "Geography"
        , "CivicEducation", "English"
        , "EducationNews", "CompetitionNews"
        , "Chemistry")

    private var topicList : ArrayList<String> = arrayListOf(
        "Biology", "Chemistry"
        , "CivicEducation", "CompetitionNews"
        , "EducationNews", "English"
        , "Geography", "Gymnastics"
        , "History", "IT"
        , "Literature", "Math"
        , "Physics", "SexEducation"
        , "TechnologyNews")

    private var avatarsList : ArrayList<String> = arrayListOf(
        "Topic/Biology/ava_biology.png"
        , "Topic/Chemistry/ava_chemistry.png"
        , "Topic/CivicEducation/ava_civic_education.png"
        , "Topic/English/ava_english.png"
        , "Topic/Geography/ava_geography.png"
        , "Topic/History/ava_history.png"
        , "Topic/IT/ava_IT.png"
        , "Topic/Literature/ava_literature.png"
        , "Topic/Math/ava_math.png"
        , "Topic/Physics/ava_physics.png"
    )

    private var authorList : ArrayList<String> = arrayListOf(
                                         "KGfDEujO5rg0M72opLglzmFKmWs2"
                                        ,"NDR5YZK6KTQiqRARMmD0Ag0ZKkE3"
                                        ,"RT2HkmWqO2MwrJujHpCcI3LITyN2"
                                        ,"XEVFthYbLFfQFQyVvsslaK0Tc3k1"
                                        ,"rSR8vR8JcjP5S6xcpcuzBiqxdHa2"
                                        ,"tstie06MvmbIOt3gi4iCZPIPYUt2")


    private fun getDate (milis : Long) : String {
        var formatter = SimpleDateFormat(dateFormat)
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = milis
        return formatter.format(calendar.time)
    }

    private fun spawnId () : String {
        // sao đó cộng dần dần lên để đảm bảo id không trùng
        currentID += 1
        return currentID.toString()
    }

    private fun spawnTag () : String = tagsEx.getRandomItem()

    private fun spawnTopic () : String = topicList.getRandomItem()

    private fun spawnAvatar () : String = avatarsList.getRandomItem()

    private fun spawnAuthor () : String = authorList.getRandomItem()

    private fun spawnReputation () : Int = Random.nextInt(0, 100)

    private fun spawnViews () : Int = Random.nextInt(0, 100)

    private fun spawnClaps () : Int = Random.nextInt(0, 100)

    private fun spawnPostTime() : Long = Random.nextLong(0, (start-1549065600000)/ONE_DAY_IN_MILIS) * ONE_DAY_IN_MILIS + 1549065600000

    private fun spawnScoreHotDayLifeCycle (storyReceiver : Stories) {
        val threeDaysScore = Random.nextInt(200, 1000)
        val fiveDaysScore = Random.nextInt(threeDaysScore , 1000)
        val sevenDaysScore = Random.nextInt(fiveDaysScore , 1000)
        storyReceiver.ThreeHotDayCycle = threeDaysScore.toLong()
        storyReceiver.FiveHotDayCycle = fiveDaysScore.toLong()
        storyReceiver.SevenHotDayCycle = sevenDaysScore.toLong()
    }

    val ONE_DAY_IN_MILIS = 86_400_000
    private fun spawnHotDayLifeCycle (storyReceiver: Stories, startTime : Long) {
        for (i in 0..2) {0
            storyReceiver.ThreeHotDayLifeCycle.add(getDate(startTime + i * ONE_DAY_IN_MILIS))
        }

        for (i in 0..4) {
            storyReceiver.FiveHotDayLifeCycle.add(getDate(startTime + i * ONE_DAY_IN_MILIS))
        }

        for (i in 0..6) {
            storyReceiver.SevenHotDayLifeCycle.add(getDate(startTime + i * ONE_DAY_IN_MILIS))
        }
    }

    private fun spawnReputationLifeCycle (storyReceiver: Stories, postTime: Long) {
        for (i in 0..4) {
            storyReceiver.ReputationLifeCycle.add(getDate(postTime + i * ONE_DAY_IN_MILIS))
        }
    }

    private fun spawnIsHotAndReputation (storyReceiver : Stories) {
        storyReceiver.isHot = Random.nextInt(0, 2) == 1
        storyReceiver.isRep = Random.nextInt(0, 2) == 1
    }

    private fun createAStories () : Stories {
        return Stories().apply {
            Avatar = spawnAvatar()
            Id = spawnId()
            Tag = spawnTag()
            PostedTime = spawnPostTime()
            Claps = spawnClaps()
            Views = spawnViews()
            Reputation = spawnReputation()
            Author = spawnAuthor()
            Topic = spawnTopic()
            spawnScoreHotDayLifeCycle(this)
            spawnHotDayLifeCycle(this, Random.nextLong(0, (end-start)/ONE_DAY_IN_MILIS) * ONE_DAY_IN_MILIS + start)
            spawnIsHotAndReputation(this)
            spawnReputationLifeCycle(this, PostedTime)
        }
    }

    fun spawnStories (count : Int) : ArrayList<Stories> {
        var list = ArrayList<Stories>()
        for (i in 1..count) {
            var story = createAStories()
            list.add(story)
            Log.e("====", "===============================")
            Log.e("Id", story.Id)
            Log.e("Topic", story.Topic)
            Log.e("Tag", story.Tag)
            Log.e("Avatar", story.Avatar)
            Log.e("Author", story.Author)
            Log.e("PostedTime", story.PostedTime.toString())
            Log.e("Views", story.Views.toString())
            Log.e("Claps", story.Claps.toString())
            Log.e("Rep", story.Reputation.toString())
            Log.e("ThreeHotDayLifeCycle", story.ThreeHotDayLifeCycle.toString())
            Log.e("FiveHotDayLifeCycle", story.FiveHotDayLifeCycle.toString())
            Log.e("SevenHotDayLifeCycle", story.SevenHotDayLifeCycle.toString())
            Log.e("ThreeHotDayCycle", story.ThreeHotDayCycle.toString())
            Log.e("FiveHotDayCycle", story.FiveHotDayCycle.toString())
            Log.e("SevenHotDayCycle", story.SevenHotDayCycle.toString())
            Log.e("ReputationLifeCycle", story.ReputationLifeCycle.toString())
            Log.e("Is hot", story.isHot.toString())
            Log.e("Is rep", story.isRep.toString() + " " + story.Reputation)
        }
        return list
    }

    fun <E> ArrayList<E>.getRandomItem () : E {
        if (size != 0) {
            var randomId = Random.nextInt(0, size - 1)
            return this[randomId]
        } else {
            throw IndexOutOfBoundsException()
        }
    }
}