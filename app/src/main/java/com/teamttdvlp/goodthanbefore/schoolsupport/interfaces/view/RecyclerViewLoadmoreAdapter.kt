package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
const val LOADED_ITEM_VIEW = 0
const val WAITING_LOAD_VIEW = 1

abstract class  RecyclerViewLoadmoreAdapter <T:RecyclerView.ViewHolder>(var context : Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

     var isLoading = false

     var isEndOfList = false

     var layoutManager : LinearLayoutManager? = null

     var mRecyclerView : RecyclerView? = null
     private var mItemClickListener : OnItemClickListener? = null

    fun startLoadingState () {
        isLoading = true
    }

    fun endLoadingState () {
        isLoading = false
    }

    fun stillHasUnloadedData (z : Boolean) {
        isEndOfList = !z
    }

    fun adaptFor (recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        layoutManager = recyclerView.layoutManager as LinearLayoutManager
    }

    fun addOnScrollListener (onScrollListener : OnScrollListener) {
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isEndOfList && !isLoading) {
                    if (layoutManager?.findLastVisibleItemPosition() == getCount()) {
                        onScrollListener.onScrollToLastElement()
                    }
                }

                if (layoutManager?.findFirstCompletelyVisibleItemPosition() == 0) {
                    onScrollListener.onScrollToFirstElement()
                } else {
                    onScrollListener.onScroll()
                }
            }
        })
    }

    fun addOnItemClickedListener (listener:OnItemClickListener) {
        mItemClickListener = listener
    }

    fun getOnItemCLickedListener () : OnItemClickListener? {
        return mItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LOADED_ITEM_VIEW) {
            var v = onCreateItemViewHolder(parent, viewType)
            return v
        } else {
            createWaitViewHolder(parent, viewType)
        }
    }

    abstract fun onCreateItemViewHolder (parent: ViewGroup, viewType: Int) : T
    abstract fun createWaitViewHolder (parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder
    override fun getItemCount() : Int {
        return if (!isEndOfList)
        // Add one more element to show loading view
            getCount() + 1
        else
            getCount()
    }

    abstract fun getCount() : Int

    override fun getItemViewType (position: Int) : Int {
        return if (!isEndOfList) {
            if (position < getCount())
                LOADED_ITEM_VIEW
            else
                WAITING_LOAD_VIEW
        } else
            LOADED_ITEM_VIEW
    }

    override fun onBindViewHolder(holder:RecyclerView.ViewHolder, position: Int) {
        var safeHolder : T? = holder as? T
        if (safeHolder != null) {
            if (position < getCount()) {
                onBind(safeHolder, position)
                }
            }
        }

    abstract fun onBind (holder: T, position: Int)

    interface OnScrollListener {
        fun onScrollToFirstElement ()

        fun onScrollToLastElement ()

        fun onScroll ()
    }

    interface OnLoadMoreListener {
        fun loadMore()
    }

    interface OnItemClickListener {
        fun onClicked (position: Int)
    }
}