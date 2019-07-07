package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Story
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_top.*

class TopFragment : androidx.fragment.app.Fragment() {

    lateinit var adapter : PostRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    private fun addControls() {
        var mockList = ArrayList<Stories>()
//        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.btn_clear_clicked)!!))
//        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.btn_clear_clicked)!!))
//        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.btn_clear_clicked)!!))
//        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.btn_clear_clicked)!!))
        adapter = PostRecyclerViewAdapter(mockList, context!!)
        top_rcv_top.adapter = adapter
        top_rcv_top.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun addEvents() {

    }

}
