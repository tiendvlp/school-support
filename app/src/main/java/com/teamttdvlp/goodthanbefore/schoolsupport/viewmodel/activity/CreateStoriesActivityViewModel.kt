package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.CreateStoryActivity

/**
 * @see CreateStoriesActivity
 */
class CreateStoriesActivityViewModel : ViewModel() {
    lateinit var activityResultListener: CreateStoryActivity.OnActivityResultListener
}