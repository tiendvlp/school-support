package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.activity.WriteStoryActivityBinding

class CreateStoryActivity : AppCompatActivity() {
    lateinit var mViewBinding : WriteStoryActivityBinding
    lateinit var activityResultListener: CreateStoryActivity.OnActivityResultListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_story)
        addControls()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        activityResultListener.onActivityResultListener(requestCode, resultCode, data)
//    }

    private fun addControls() {

    }

    interface OnActivityResultListener {
        fun onActivityResultListener (requestCode: Int, resultCode: Int, data: Intent?)
    }
}
