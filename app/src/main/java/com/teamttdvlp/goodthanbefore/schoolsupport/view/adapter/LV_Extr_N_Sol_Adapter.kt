package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.drawgraphmodule.Point
import com.teamttdvlp.goodthanbefore.schoolsupport.R


class LV_Extr_N_Sol_Adapter (var context : Context?, var list : ArrayList<Point>?) : BaseAdapter() {

    private var describeContent : DescribeContent? = null

    fun describeContent (describeContent: DescribeContent){
        this.describeContent = describeContent
    }

    override fun getItem(position: Int): Any {
        return list!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list!!.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.child_ex_n_sol, parent, false)
        var txt_info = view.findViewById(R.id.txt_info) as TextView
        txt_info.text = describeContent!!(list!!.get(position), position)
        return view
    }

}
typealias DescribeContent = ((point : Point, pos : Int) -> String)
