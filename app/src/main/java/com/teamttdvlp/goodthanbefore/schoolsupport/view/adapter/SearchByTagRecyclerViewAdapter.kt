package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.SearchByTagsFragment

class SearchByTagRecyclerViewAdapter(var context : Context, var itemList : ArrayList<String>, var recyclerView : RecyclerView) : RecyclerView.Adapter<SearchByTagRecyclerViewAdapter.DataViewHolder>() {

    private var onItemClickListener : OnItemClickListener? = null

    var onClickListener : (View) -> Unit = {
        var pos = recyclerView.indexOfChild(it)
        onItemClickListener?.onItemClickListener(it, pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchByTagRecyclerViewAdapter.DataViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_lv_search_by_tag, parent, false)
        view.setOnClickListener(onClickListener)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: SearchByTagRecyclerViewAdapter.DataViewHolder, position: Int) {
        holder.tag.text = itemList[position]
    }

    fun setOnItemClickListener (onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class DataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tag : TextView = itemView.findViewById(R.id.txt_tag)
    }

    interface OnItemClickListener {
        fun onItemClickListener (view : View, pos : Int)
    }
}