package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.SubmitStoryFragmentBinding

import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.PostNewStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.notifiChanged
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment.SubmitStoryFragmentViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.lang.Exception

class SubmitStoryFragment : Fragment(), PostNewStoryEvent {
    private var mStory : Stories = Stories()
    private lateinit var mBinding : SubmitStoryFragmentBinding
    private lateinit var mViewModel : SubmitStoryFragmentViewModel

    companion object {
        fun newInstance (date:String, author:String) : SubmitStoryFragment {
            var mDialog = SubmitStoryFragment()
            var bundle = Bundle()
            bundle.putString("Date", date)
            bundle.putString("Author", author)
            mDialog.arguments = bundle
            return mDialog
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_submit_story, container, false)
        mBinding.lifecycleOwner = this
        mViewModel = getViewModel()
        Log.d("accc", "chayroido")
        mBinding.viewModel = mViewModel
        mBinding.txtDate.text = mViewModel.getCurrentDate()
        mBinding.txtAuthor.text = CurrentUser.currentUser!!.DisplayName
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }
    private val listTopic : Array<String> = arrayOf("Biology", "Chemistry"
        , "CivicEducation", "CompetitionNews"
        , "EducationNews", "English"
        , "Geography", "Gymnastics"
        , "History", "IT"
        , "Literature", "Math"
        , "Physics", "SexEducation"
        , "TechnologyNews")

    private fun addControls() {
        val topicAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, listTopic)
        topicAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
//        mBinding.spnTopic.adapter = topicAdapter
    }

    private fun addEvents() {
        mBinding.btnCancel.setOnClickListener{
            activity!!.supportFragmentManager.popBackStack()
        }

        mBinding.imgAvatar.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(context!!, this)
        }

        mBinding.btnSubmit.setOnClickListener{
            setUpStory()
            if (!checkData()) {
                Toast.makeText(context, "Please complete the information above", Toast.LENGTH_LONG).show()
            } else {
            Log.d("StoriesTest", "Title: ${mViewModel.currentStory.value!!.Title}")
            Log.d("StoriesTest", "Author: ${mViewModel.currentStory.value!!.Author}")
            Log.d("StoriesTest", "Tag: ${mViewModel.currentStory.value!!.Tag}")
            Log.d("StoriesTest", "Date: ${mViewModel.currentStory.value!!.PostedTime}")
            Log.d("StoriesTest", "Review: ${mViewModel.currentStory.value!!.Review}")
            Log.d("StoriesTest", "Content: ${mViewModel.currentStory.value!!.Content}")
            val bitmap  = (mBinding.imgAvatar.drawable as BitmapDrawable).bitmap
            mViewModel.postStory(this, bitmap)
            showProgressbar(true)
        }}
    }

    private fun checkData() : Boolean {
        var data  = mViewModel.currentStory.value!!
        if (data.Title.length < 16) {
            return false
        }
        if (data.Review.length < 26) {
            return false
        }
        if (data.Topic.isEmpty()) {
            return false
        }
//        if (mBinding.imgAvatar.drawable== null) {
//            return false
//        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("avatarURi", "chay")
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                Log.d("avatarURi", result.toString())
//                mBinding.imgAvatar.setImageURI(resultUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Log.e("loiroine ", "EditProfileActivity.kt \n onActivityResult \n $error")
            }
        }
    }

    fun showProgressbar (isLoading : Boolean) {
        if (isLoading) {
            mBinding.mainLayout.visibility = View.GONE
            mBinding.progress.visibility =  View.VISIBLE
        } else {
            mBinding.mainLayout.visibility = View.VISIBLE
            mBinding.progress.visibility = View.GONE
        }
    }

    override fun onPostNewStorySuccess() {
        showProgressbar(false)
        Toast.makeText(context, "Post Story Success", Toast.LENGTH_LONG).show()
        activity!!.supportFragmentManager.popBackStack()
    }

    override fun onPostNewStoryFailed(e: Exception?) {
        showProgressbar(false)
        Log.d("PostStory", "Failed: ${e?.message}")
        Toast.makeText(context, "Post Story Failed", Toast.LENGTH_LONG).show()
    }

    private fun setUpStory () {
        mViewModel.currentStory.value!!.PostedTime = System.currentTimeMillis()
        mViewModel.currentStory.value!!.Content = arguments?.getString("Content")!!
        mViewModel.currentStory.value!!.Author = CurrentUser.currentUser!!.Id
        mViewModel.currentStory.value!!.AuthorDisplayName = CurrentUser.currentUser!!.DisplayName
        mViewModel.currentStory.value!!.Topic = mBinding.spnTopic.selectedItem.toString()
        mViewModel.currentStory.notifiChanged()
        Log.d("SubmitStory", "content: " + mStory.Content)
    }

}