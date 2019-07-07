package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.customview.CheckBox
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError

class InterestRecylerViewAdapter (var context: Context, var item_list : ArrayList<Interest> = ArrayList()): RecyclerView.Adapter<InterestRecylerViewAdapter.DataViewHolder>() {

    var recyclerView : RecyclerView? = null

    var selectedItemList = ArrayList<Interest>()

    fun adaptFor (recyclerView : RecyclerView) {
        this.recyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView = layoutInflater.inflate(R.layout.item_lv_select_interest, parent, false)
        return DataViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return item_list.size

    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        var item = item_list[position]
        logError("------- $position", "------------------- $item")
        FirebaseStorage.getInstance().getReference(item.avatar)
            .downloadUrl
            .addOnCompleteListener {
                Picasso.get().load(it.result).into(holder.img_avatar)
            }
        holder.txt_name.text = item.name
        holder.txt_description.text = item.description
        holder.rbtn_is_selected.isChecked = item.ticked
        if (item.ticked && (!selectedItemList.contains(item))) {
            selectedItemList.add(item)
        }

        holder.onChosenChangeListener = object : OnItemChooseChangeListener {
            override fun onChosenChange(isChosen: Boolean, pos: Int) {
                val clickedItem = item_list[pos]

                if (isChosen && !selectedItemList.contains(clickedItem))
                    selectedItemList.add(clickedItem)

                else if (!isChosen && selectedItemList.contains(clickedItem))
                    selectedItemList.remove(clickedItem)

                item_list[pos].ticked = isChosen
                logError("Duma $pos", item_list[pos].toString())
            }
        }
    }

    inner class DataViewHolder: RecyclerView.ViewHolder {
        var img_avatar : ImageView = itemView.findViewById(R.id.img_avatar)
        var txt_name : TextView = itemView.findViewById(R.id.txt_interest_name)
        var txt_description  : TextView = itemView.findViewById(R.id.txt_description)
        var rbtn_is_selected : CheckBox = itemView.findViewById(R.id.rbtn_is_selected)
        var onChosenChangeListener : OnItemChooseChangeListener? = null

        constructor(itemView: View ) : super (itemView) {
            rbtn_is_selected.setOnCheckedChangeListener {
                _, isChecked ->
                onChosenChangeListener?.onChosenChange(isChecked, adapterPosition)
            }
        }
    }

    interface OnItemChooseChangeListener {
        fun onChosenChange(isChosen : Boolean, pos : Int)
    }
}