package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SearchResultActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.SearchByTagRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_search_by_tags.*

class SearchByTagsFragment : androidx.fragment.app.Fragment() {

    lateinit var adapter : SearchByTagRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_by_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
    }

    private fun addControls() {
        var list = ArrayList<String>()
        list.add("Toán hình học 12")
        list.add("Toán đại số 12")
        list.add("Hóa bài tập chương IV - Hóa 12")
        list.add("Bài tập sinh học 11")
        list.add("Bài tập tin học C/C++")
        list.add("Đại cương giáo dục quốc phòng")
        list.add("Toán cao cấp")
        list.add("Văn mẫu")
        adapter = SearchByTagRecyclerViewAdapter(context!!, list, lv_tags)
        lv_tags.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        lv_tags.adapter = adapter
        adapter.setOnItemClickListener (object : SearchByTagRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, pos: Int) {
                Log.e("Pos", pos.toString())
                startActivity(Intent(context, SearchResultActivity::class.java))
            }
        })
    }
}