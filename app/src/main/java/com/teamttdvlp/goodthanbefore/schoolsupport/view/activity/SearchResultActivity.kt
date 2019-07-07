package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.SearchResultViewPagerAdapter
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity() {

    lateinit var adapter : SearchResultViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        addControls()
        addEvents()
    }

    private fun addEvents() {

    }

    private fun addControls() {
        var layoutInflater = LayoutInflater.from(this)
        var view1 = layoutInflater.inflate(R.layout.item_tablayout_searchview, activity_search_result, false) as TextView
        var view2 = layoutInflater.inflate(R.layout.item_tablayout_searchview, activity_search_result, false) as TextView
        view1.layoutParams.width = WRAP_CONTENT
        view1.text = "Top"
        view1.typeface = Typeface.DEFAULT_BOLD
        view2.text = "History"
        adapter = SearchResultViewPagerAdapter(supportFragmentManager)
        search_result_vpg_search.adapter = adapter
        search_result_tablayout_directory.setupWithViewPager(search_result_vpg_search)
        search_result_vpg_search.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var prevPagePos = 0
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevPagePos != position) {
                    (search_result_tablayout_directory.getTabAt(position)!!.customView as TextView).typeface = Typeface.DEFAULT_BOLD
                    (search_result_tablayout_directory.getTabAt(prevPagePos)!!.customView as TextView).typeface = Typeface.DEFAULT
                    prevPagePos = position
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        search_result_tablayout_directory.getTabAt(0)?.customView = view1
        search_result_tablayout_directory.getTabAt(1)?.customView = view2
    }
}
