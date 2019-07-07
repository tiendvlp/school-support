package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SetUserInterestEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.InterestRecylerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.InterestActivityViewModel
import kotlinx.android.synthetic.main.activity_interest.*
import java.lang.Exception

private const val REQUIRE_SELECTED_CONTENT_COUNT = 3
class InterestActivity : AppCompatActivity(), SetUserInterestEvent {

    lateinit var rcv_interest_adapter : InterestRecylerViewAdapter

    lateinit var mViewModel : InterestActivityViewModel

    var interestList = ArrayList<Interest>()

    var user : User? = null

    lateinit var dialogInterestError : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)
        user = CurrentUser.currentUser
        addControl()
        addEvents()
        loadData()
    }

    fun addControl () {
        mViewModel = getViewModel()
        rcv_interest_adapter = InterestRecylerViewAdapter(this, interestList)
        rcv_interest_adapter.adaptFor(lv_interest)
        setUpErrorDialog()
    }

    fun tickInterest () {

    }

    fun addEvents () {
        btn_next.setOnClickListener {
            if (rcv_interest_adapter.selectedItemList.size >= REQUIRE_SELECTED_CONTENT_COUNT)
            {
                try {
                val userInterest: ArrayList<String> = ArrayList()
                    for (interest in rcv_interest_adapter.selectedItemList) {
                        userInterest.add(interest.name)
                        Log.e("Name", "name? ${interest.name}")
                    }
                    user!!.Interests.clear()
                    user!!.Interests.addAll(userInterest)
                    mViewModel.setUserInterest(user!!.Id,  userInterest, this)
                } catch (ex :Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }
    override fun onSetUserInterestEventSuccess() {
        startActivity(Intent(this, MainActivity::class.java).putExtra("User", user))
        finish()
    }

    override fun onSetUserInterestEventFailed(e: Exception?) {
        showErrorDialog()
    }

    fun loadData () {
        val onLoadSuccess : (ArrayList<Interest>) -> Unit = {
            if (user != null)
                for (topic in it) {
                    topic.ticked = user!!.Interests.contains(topic.name)
                }
            else {
                logError("null roi:Dit me")
            }
            Log.d("UserInteres", user!!.Interests.toString())
            interestList.clear()
            interestList.addAll(it)
            rcv_interest_adapter.notifyDataSetChanged()
        }

        val onLoadInfoFailed : (Exception) -> Unit = {
            showErrorDialog()
        }

        mViewModel.loadData (onLoadSuccess, onLoadInfoFailed)
    }

    fun setUpErrorDialog () {
        var dialogBuilder = AlertDialog.Builder(this)
        var dialogView = layoutInflater.inflate(R.layout.dialog_interest_error, null)
        var txtSkipAndWorkOffline = dialogView.findViewById<TextView>(R.id.txt_skip_and_work_offline)
        txtSkipAndWorkOffline.setOnClickListener {
            startActivity(Intent(this, OfflineToolActivity::class.java))
        }

        dialogBuilder.setView(dialogView)
        dialogInterestError = dialogBuilder.create()
    }

    fun showErrorDialog () {
        dialogInterestError.show()
        dialogInterestError.setCanceledOnTouchOutside(true)
    }
}
