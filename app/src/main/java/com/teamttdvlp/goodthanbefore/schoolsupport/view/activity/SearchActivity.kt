package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SearchStoriesEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.SearchDirectoryViewPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.Exception

class SearchActivity : AppCompatActivity(), SearchStoriesEvent {

    lateinit var adapter : SearchDirectoryViewPagerAdapter

    lateinit var mViewModel : SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        addControls()
        addEvents()
        val keyword = intent?.extras?.getString("Keyword")
        excuteSearching(keyword.toString())
    }

    fun addControls () {
        mViewModel = getViewModel()
        var view1 = LayoutInflater.from(this).inflate(R.layout.item_tablayout_searchview, activity_search, false) as TextView
        var view2 = LayoutInflater.from(this).inflate(R.layout.item_tablayout_searchview, activity_search, false) as TextView
        var view3 = LayoutInflater.from(this).inflate(R.layout.item_tablayout_searchview, activity_search, false) as TextView
        view1.text = "Stories"
        view1.typeface = Typeface.DEFAULT_BOLD
        view2.text = "People"
        view3.text = "Tags"
        adapter = SearchDirectoryViewPagerAdapter(supportFragmentManager)
        search_vpg_search.adapter = adapter
        search_tablayout_directory.setupWithViewPager(search_vpg_search)
        search_tablayout_directory.getTabAt(0)?.customView = view1
        search_tablayout_directory.getTabAt(1)?.customView = view2
        search_tablayout_directory.getTabAt(2)?.customView = view3

        search_vpg_search.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var prevPageSelectedPos = 0
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position != prevPageSelectedPos) {
                    (search_tablayout_directory.getTabAt(position)?.customView as TextView).typeface = Typeface.DEFAULT_BOLD
                    (search_tablayout_directory.getTabAt(prevPageSelectedPos)?.customView as TextView).typeface = Typeface.DEFAULT
                    prevPageSelectedPos = position
                }
            }

        })

    }

    fun addEvents () {
        edt_search.setOnEditorActionListener (object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    excuteSearching(edt_search.text.toString())
                    return true
                } else {
                    return false
                }
            }
        })
    }

    fun excuteSearching (keyword : String?) {
        if (keyword != null) {
            progress_bar.visibility = View.VISIBLE
            mViewModel.searchStoriesByTitle(keyword, this)
        } else {
            Toast.makeText(this, "Vui lòng nhập từ khóa", Toast.LENGTH_LONG).show()
            logError("Error: Null keyword")
        }
    }

    /**
     * @see FragmentSearchByStories is observer of this value
     */
    override fun onSearchStoriesSuccess(resultStories: ArrayList<Stories>) {
        mViewModel.resultStories.value = resultStories
        progress_bar.visibility = View.GONE
    }

    override fun onSearchStoriesFailed(e: Exception?) {
        Toast.makeText(this, "Đã xảy ra lỗi", Toast.LENGTH_LONG).show()
    }
}
