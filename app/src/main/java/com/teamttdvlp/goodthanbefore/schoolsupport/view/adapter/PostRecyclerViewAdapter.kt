package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PostRecyclerViewAdapter  (
    listItem: ArrayList<Stories>,
    context: Context) : RecyclerViewLoadmoreAdapter<PostRecyclerViewAdapter.LoadedViewDataHolder>(context) {
    private var listItem = listItem
    private var onBookMarkBtnClickListener:OnItemClickListener? = null
    override fun getCount(): Int {
            return listItem.size
    }

    fun addOnBookMarkClickListener (listener:OnItemClickListener) {
        onBookMarkBtnClickListener = listener
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): LoadedViewDataHolder {
        Log.e("On Create View Holder", "Loaded item")
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_lv_story, parent, false)
        var viewHolder = LoadedViewDataHolder(itemView)
        viewHolder.onItemClickListener = getOnItemCLickedListener()
        viewHolder.onBookMarkClickListener = onBookMarkBtnClickListener
        return viewHolder
    }

    override fun createWaitViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.e("On Create View Holder", "Waiting item")
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_lv_waiting_loading, parent, false)
        return WaitingViewDataHolder(itemView)
    }

    override fun onBind(holder: LoadedViewDataHolder, position: Int) {
        val story = listItem[position]
        story?.let {
            holder.apply {
                if (!story.Avatar.isNullOrEmpty())
                Picasso.get().load(story.Avatar).into(imgAvatar)
                txtPostTitle.text = story.Title
                txtAuthor.text = story.AuthorDisplayName
                txtTag.text = story.Topic
                txtDate.text = convertTimeMilisToDate(story.PostedTime)
            }
        }
    }

    private fun convertTimeMilisToDate (timilis:Long): String? {
        var format:DateFormat = SimpleDateFormat("dd/MM/yyyy")
        var result:Date = Date(timilis)
        return format.format(result)
    }

        class LoadedViewDataHolder : RecyclerView.ViewHolder {
            var imgAvatar : ImageView
            var txtPostTitle : TextView
            var txtAuthor : TextView
            var txtDate : TextView
            var txtTag : TextView
            var btnBookmark: Button
            var onItemClickListener : OnItemClickListener? = null
            var onBookMarkClickListener : OnItemClickListener? = null
            constructor(item_view : View) : super (item_view) {
                 imgAvatar= item_view.findViewById(R.id.img_avatar)
                 txtPostTitle = item_view.findViewById(R.id.txt_post_title)
                 txtAuthor  = item_view.findViewById(R.id.txt_author)
                 txtDate  = item_view.findViewById(R.id.txt_date)
                 txtTag  = item_view.findViewById(R.id.txt_tag)
                 btnBookmark = item_view.findViewById(R.id.rbtn_bookmark)
                item_view.setOnClickListener({
                    onItemClickListener?.onClicked(adapterPosition)
                })

                btnBookmark.setOnClickListener {
                    onBookMarkClickListener?.onClicked(adapterPosition)
                }
            }
        }

        class WaitingViewDataHolder (item_view : View) : RecyclerView.ViewHolder(item_view)
    }
