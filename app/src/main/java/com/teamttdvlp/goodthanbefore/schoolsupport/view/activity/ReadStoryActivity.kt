package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.activity.ReadStoryActivityBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.DateSupport
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.*
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.ReadStoriesActivityViewModel
import java.lang.Exception

class ReadStoryActivity : AppCompatActivity(), ReadInfoEvent, NewBookmarkEvent, DeleteBookmarkEvent, NewLikeStoryEvent, DeleteLikeStoryEvent, NewHistorialStoryEvent, IsStoryExistInBookmarkListEvent, IsStoryExistInLikeListEvent {
    lateinit var mBinding : ReadStoryActivityBinding
    lateinit var mViewModel : ReadStoriesActivityViewModel
    lateinit var mAuthor : User

    var likeProcessDone = false
    var historialProcessDone = false
    var bookmarkProcessDone = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_stories)
        setUp()
        addControls()
        addEvents()
    }

    private fun setUp () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_read_stories)
        mViewModel = getViewModel()
        mViewModel.story = intent.getSerializableExtra("Story") as Stories
        Log.d("ReadStory", mViewModel.story.Title)

        mBinding.txtAuthorName.text = mViewModel.story.Author
        mBinding.txtBigTitle.text = mViewModel.story.Title
        mBinding.txtContent.fromHtml(mViewModel.story.Content)
        mBinding.txtShortDescription.text = mViewModel.story.Review
        mBinding.txtPostTime.text = DateSupport.getDateByTimeMillis(mViewModel.story.PostedTime)
        mViewModel.getAuthor(mViewModel.story.Author, this)
        storyThreeCycleUpdater["threeHotDayCycle"] = mViewModel.story.ThreeHotDayCycle
        storyThreeLifeCycleUpdater["threeHotDayLifeCycle"] = ArrayList()
        storyThreeLifeCycleUpdater["threeHotDayLifeCycle"]!!.addAll(mViewModel.story.ThreeHotDayLifeCycle)
        Log.d("authorla", mViewModel.story.Author + "aaa")
//        mViewModel.previousBtnBookmarkStatus.observe(this,object:Observer<Boolean> {
//            override fun onChanged(t: Boolean?) {
//                mBinding.btnBookmark.isChecked = t!!
//            }
//
//        })
        mViewModel.setHistory(mViewModel.story.Id, this)
        mViewModel.isStoryExistInBookmarkList(mViewModel.story.Id,this)
        mViewModel.isStoryExistInLikedList(mViewModel.story.Id,this)
        loading(true)
    }

    fun allFirstProcesDone () {
        if (likeProcessDone && bookmarkProcessDone && historialProcessDone) {
            loading(false)
        }
    }

    fun loading (isLoading : Boolean) {
        if (isLoading) {
            mBinding.layoutMain.visibility = View.GONE
            mBinding.layoutProcessing.visibility = View.VISIBLE
        } else {
            mBinding.layoutMain.visibility = View.VISIBLE
            mBinding.layoutProcessing.visibility = View.GONE
        }
    }

    override fun onReadInfoSuccess(user: User?) {
        mAuthor = user!!
        mBinding.txtAuthorName.text = mAuthor.DisplayName
        if (!mAuthor.Avatar.isNullOrEmpty()) {
            Picasso.get().load(mAuthor.Avatar).into(mBinding.imgAvatar)
        }
    }

    override fun onReadInfoFailed(e: Exception?) {
        Toast.makeText(this, "Get Author Info failed", Toast.LENGTH_LONG).show()
        Log.d("ReadStories", "get Author failed ${e?.message}")
    }

    private fun addControls() {

    }

    private fun addEvents() {
        mBinding.btnBookmark.setOnCheckedChangeListener({v, isChecked ->
            if (mViewModel.isBtnBookmarkProcessing.value!!) {
                mBinding.btnBookmark.isChecked = mViewModel.previousBtnBookmarkStatus.value!!
            } else {
                if (isChecked) {
                    mViewModel.setBookmark(mViewModel.story.Id, this)
                } else {
                    mViewModel.unSetBookmark(mViewModel.story.Id, this)
                }
                mViewModel.isBtnBookmarkProcessing.value = true
                mViewModel.previousBtnBookmarkStatus.value = isChecked
            }
        })

        mBinding.btnClap.setOnCheckedChangeListener({v, isChecked ->
            if (mViewModel.isBtnLikeProcessing.value!!) {
                mBinding.btnBookmark.isChecked = mViewModel.previousBtnBookmarkStatus.value!!
            } else {
                if (isChecked) {
                    checkCycle(1)
                    mViewModel.setLiked(mViewModel.story.Id, this)
                    updateStory()
                } else {
                    checkCycle(-1)
                    mViewModel.unSetLike(mViewModel.story.Id, this)
                    updateStory()
                }
                mViewModel.isBtnLikeProcessing.value = true
                mViewModel.previousBtnLikeStatus.value = isChecked
            }
        })
    }
    var storyThreeLifeCycleUpdater : HashMap<String, ArrayList<String>> = HashMap()
    var storyThreeCycleUpdater : HashMap<String, Long> = HashMap()
    private fun updateStory () {
        FirebaseFirestore.getInstance().collection("Stories").document(mViewModel.story.Id)
            .set(storyThreeLifeCycleUpdater, SetOptions.merge())
        FirebaseFirestore.getInstance().collection("Stories").document(mViewModel.story.Id)
            .set(storyThreeCycleUpdater, SetOptions.merge())

    }

    private fun checkCycle (like : Int) {
        var today  = System.currentTimeMillis()
        if (!storyThreeLifeCycleUpdater["threeHotDayLifeCycle"]!!.contains(DateSupport.getDateByTimeMillis(today))) {
            storyThreeLifeCycleUpdater["threeHotDayLifeCycle"]!!.clear()
            storyThreeLifeCycleUpdater["threeHotDayLifeCycle"]!!.addAll(calculateNewCycle(3))
        }
        storyThreeCycleUpdater["threeHotDayCycle"] = storyThreeCycleUpdater["threeHotDayCycle"]!! +  like
        mViewModel.story.ThreeHotDayLifeCycle.clear()
        mViewModel.story.ThreeHotDayLifeCycle.addAll(storyThreeLifeCycleUpdater["threeHotDayLifeCycle"]!!)
        mViewModel.story.ThreeHotDayCycle =  storyThreeCycleUpdater["threeHotDayCycle"]!!
    }
    val ONE_DAY_IN_MILIS = 86_400_000
    private fun calculateNewCycle (cycle : Int) : ArrayList<String>{
        var today  = System.currentTimeMillis()
        var deltaDate = ((today - mViewModel.story.PostedTime)/ONE_DAY_IN_MILIS).toLong() + 1
        var sodu =  (deltaDate % cycle)
        var ngaydautien = (today - (sodu-1)*ONE_DAY_IN_MILIS)
        return createCycle(ngaydautien, cycle)
    }

    fun createCycle (startTime:Long, cycleDay:Int) : ArrayList<String> {
        var result : ArrayList<String> = ArrayList()
        for (i in 0..cycleDay-1) {
            result.add(DateSupport.getDateByTimeMillis(startTime + i * ONE_DAY_IN_MILIS))
        }
        return result
    }

    override fun onNewBoomarkSuccess() {
        mViewModel.isBtnBookmarkProcessing.value = false
    }
    override fun onDeleteBookmarkSuccess() {
        mViewModel.isBtnBookmarkProcessing.value = false
    }

    override fun onNewBookmarkFailed(e: Exception?) {
        mViewModel.isBtnBookmarkProcessing.value = false
    }


    override fun onDeleteBookmarkFailed(e: Exception?) {
        mViewModel.isBtnBookmarkProcessing.value = false
    }

    override fun onNewLikeStorySuccess() {
        mViewModel.isBtnLikeProcessing.value = false
    }

    override fun onNewLikeStoryFailed(e: Exception?) {
        mViewModel.isBtnLikeProcessing.value = false
    }
    override fun onDeleteLikeStorySuccess() {
        mViewModel.isBtnLikeProcessing.value = false
    }

    override fun onDeleteLikeStoryFailed(e: Exception?) {
        mViewModel.isBtnLikeProcessing.value = false
    }
    override fun onNewHistorialStorySuccess() {
        historialProcessDone= true
        allFirstProcesDone()

    }

    override fun onNewHistorialStoryFailed(e: Exception?) {
        historialProcessDone= true
        allFirstProcesDone()

    }

    override fun onStoryExistInLikeListSuccess(result: Boolean) {
        likeProcessDone= true
        mBinding.btnClap.isChecked = result
        allFirstProcesDone()
    }

    override fun onStoryExistInLikeListFailed(e: Exception?) {
        likeProcessDone= true
        allFirstProcesDone()
    }

    override fun onStoryExistInBookmarkListSuccess(result: Boolean) {
        bookmarkProcessDone= true
        mBinding.btnBookmark.isChecked = result
        allFirstProcesDone()
    }

    override fun onStoryExistInBookmarkListFailed(e: Exception?) {
        bookmarkProcessDone= true
        allFirstProcesDone()
    }


}
