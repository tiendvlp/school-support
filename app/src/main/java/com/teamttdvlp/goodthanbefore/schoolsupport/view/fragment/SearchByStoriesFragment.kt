package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.SearchByStoryFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.ReadStoryActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.SearchActivityViewModel
import kotlinx.android.synthetic.main.fragment_search_by_story.*

class  SearchByStoriesFragment : androidx.fragment.app.Fragment() {

    lateinit var mBinding : SearchByStoryFragmentBinding

    lateinit var activityViewModel : SearchActivityViewModel

    lateinit var rcvSearchAdapter : PostRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_by_story, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
    }

    private fun addControls() {
        activityViewModel = activity!!.getViewModel()
        activityViewModel.resultStories.observe(this, Observer {
            rcvSearchAdapter = PostRecyclerViewAdapter(it, context!!)
            lv_search.layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
            lv_search.adapter = rcvSearchAdapter
            rcvSearchAdapter.stillHasUnloadedData(false)
            logError("Size : ${it.size}")
            addEvents()
        })
    }

    private fun addEvents() {
        rcvSearchAdapter.addOnItemClickedListener(object : RecyclerViewLoadmoreAdapter.OnItemClickListener {
            override fun onClicked(position: Int) {
                var i : Intent = Intent(activity, ReadStoryActivity::class.java)
                i.putExtra("Story",activityViewModel.resultStories.value!![position])
                startActivity(i)
            }

        })
    }


}
