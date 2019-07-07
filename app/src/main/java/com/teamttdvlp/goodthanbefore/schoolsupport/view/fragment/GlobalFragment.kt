package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.GlobalFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.ReadStoryActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SearchBarActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.UserActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.MainActivityViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment.GlobalFragmentViewModel
import kotlinx.android.synthetic.main.fragment_global.*

class GlobalFragment : Fragment(), RecyclerViewLoadmoreAdapter.OnItemClickListener {

    private lateinit var mBinding: GlobalFragmentBinding

    private lateinit var mViewMode: GlobalFragmentViewModel
    private lateinit var adapter: PostRecyclerViewAdapter
    private lateinit var activityModel: MainActivityViewModel
    private var isLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_global, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControl()
        addEvents()
    }

    fun addControl() {
        activityModel = activity!!.getViewModel()
        mViewMode = getViewModel()

        adapter = PostRecyclerViewAdapter(mViewMode.storyData, context!!)
        lv_tester!!.adapter = adapter
        adapter.adaptFor(lv_tester)
    }

    fun addEvents() {
        global_btn_user.setOnClickListener {
            val intent = Intent(context, UserActivity::class.java)
            startActivity(intent)
        }

        adapter.addOnBookMarkClickListener(object : RecyclerViewLoadmoreAdapter.OnItemClickListener {
            override fun onClicked(position: Int) {
                Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show()
            }
        })

        global_btn_search.setOnClickListener {
            startActivity(Intent(context!!, SearchBarActivity::class.java))
        }

        adapter.addOnItemClickedListener(this)

        adapter.addOnScrollListener(object : RecyclerViewLoadmoreAdapter.OnScrollListener {
            override fun onScrollToFirstElement() {
                mBinding.globalDividerLine.apply {
                    if (visibility == VISIBLE) {
                    }
                    visibility = INVISIBLE
                }
            }

            override fun onScrollToLastElement() {
                // Bat buoc goi truoc khi load du lieu, thoi la bi bug
                adapter.startLoadingState()
                Log.d("loadne", "loading")
                loadMore()
            }

            override fun onScroll() {
                mBinding.globalDividerLine.apply {
                    if (visibility == INVISIBLE) {
                    }
                    visibility = VISIBLE
                }
            }

        })
    }

    override fun onClicked(position: Int) {
        val storyClicked: Stories = mViewMode.storyData[position]
        val intent = Intent(activity, ReadStoryActivity::class.java)
        intent.putExtra("Story", storyClicked)
        startActivity(intent)
    }

    fun loadMore() {
        var checkPointThree: Long = 0
        var checkPointFive: Long = 0
        var checkPointSeven: Long = 0
        if (mViewMode.storyData.size > 0) {
            checkPointThree = mViewMode.storyData[mViewMode.storyData.size - 1].ThreeHotDayCycle
            checkPointFive = mViewMode.storyData[mViewMode.storyData.size - 1].FiveHotDayCycle
            checkPointSeven = mViewMode.storyData[mViewMode.storyData.size - 1].SevenHotDayCycle
        } else {
            checkPointThree = Long.MAX_VALUE
            checkPointFive = Long.MAX_VALUE
            checkPointSeven = Long.MAX_VALUE
        }

        Log.d("checkpoints5: ", checkPointFive.toString())
        Log.d("checkpoints7: ", checkPointSeven.toString())
        Log.d("checkpoints3: ", checkPointThree.toString())

        mViewMode.loadHotStories(
            CurrentUser.currentUser!!.Interests,
            checkPointThree,
            checkPointFive,
            checkPointSeven,
            object : GetMultipleStories {
                override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
                    mViewMode.storyData.addAll(result)
                    Log.d("loadne", "loading")
//                if (mViewMode.storyData.size==0) {
//                    adapter.endLoadingState()
//                    adapter.isEndOfList = true
//                    return;
//                }
                    mViewMode.removeDuplicateItem()
                    adapter.notifyDataSetChanged()
                    adapter.endLoadingState()
                }

                override fun onGetMultipleStoriesFailed() {
                }

            })
    }
}
