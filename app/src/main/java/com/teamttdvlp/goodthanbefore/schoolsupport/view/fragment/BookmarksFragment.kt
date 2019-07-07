package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.BookMarkFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.ReadStoryActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.MainActivityViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment.BookmarkFragmentViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*


class BookmarksFragment : Fragment(), GetMultipleStories, RecyclerViewLoadmoreAdapter.OnScrollListener {

    private lateinit var mAdapter : PostRecyclerViewAdapter
    private lateinit var mViewModel : BookmarkFragmentViewModel
    private lateinit var mBinding : BookMarkFragmentBinding
    private lateinit var activityViewModel: MainActivityViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_bookmark, container, false)
        activityViewModel = activity!!.getViewModel()
        mViewModel = getViewModel()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
        setup()
    }

    private fun setup() {
    }

    private fun addEvents() {
        mAdapter.addOnItemClickedListener(object : RecyclerViewLoadmoreAdapter.OnItemClickListener {
            override fun onClicked(position: Int) {
                var i : Intent = Intent(activity, ReadStoryActivity::class.java)
                i.putExtra("Story", mViewModel.storyData[position])
                startActivity(i)
            }
        })
    }

    override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
        mViewModel.storyData.addAll(result)
        mAdapter.endLoadingState()
        mAdapter.notifyDataSetChanged()
        if (result.size==0) {
            mAdapter.stillHasUnloadedData(false)
        }
    }

    override fun onGetMultipleStoriesFailed() {

    }

    override fun onScrollToFirstElement() {

    }

    override fun onScrollToLastElement() {
        mAdapter.startLoadingState()
        mViewModel.getUserBookmarkStories(3, this)
    }

    override fun onScroll() {

    }

    private fun addControls() {
        mAdapter = PostRecyclerViewAdapter(mViewModel.storyData, context!!)
        mAdapter.stillHasUnloadedData(true)
        mBinding.lvPost.adapter = mAdapter
        lv_post.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mAdapter.adaptFor(mBinding.lvPost)
        mAdapter.addOnScrollListener(this)
    }

}
