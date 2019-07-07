package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalequation.ChemicalEquation
import java.util.*

@Suppress("UNCHECKED_CAST")
class RecyclerViewSearchCEqAdapter (var context : Context, var list : ArrayList<ChemicalEquation> ): RecyclerView.Adapter<RecyclerViewSearchCEqAdapter.DataViewHolder>(), Filterable {

    var filter : ChemicalSearcher = ChemicalSearcher()

    var defaultList : ArrayList<ChemicalEquation> = list

    var rcv : RecyclerView? = null

    private var onItemClick : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewSearchCEqAdapter.DataViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_search_chem_eq, parent, false)
        return DataViewHolder(itemView)
    }

    fun adaptFor(rcv: RecyclerView) {
        this.rcv = rcv
        this.rcv!!.adapter = this
        val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        this.rcv!!.layoutManager = manager
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewSearchCEqAdapter.DataViewHolder, position: Int) {
        holder.txtChemEq.text = Html.fromHtml(list[position].equation.replace("+", " + "))
        holder.onItemClickListener = object : OnItemClickListener {
            override fun onClick(view: View, equation: ChemicalEquation, position: Int) {
                onItemClick?.onClick(view, equation, position)
            }

        }
    }

    override fun getFilter(): Filter {
        return filter
    }

    fun setOnItemClickListener (onClickListener: OnItemClickListener) {
        this.onItemClick = onClickListener
    }

    inner class DataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var txtChemEq : TextView = itemView.findViewById(R.id.txt_chem_eq)

        var onItemClickListener : OnItemClickListener? = null

        init {
            var horScrollView = itemView.findViewById<HorizontalScrollView>(R.id.hor_scrview)
            horScrollView.isVerticalScrollBarEnabled = false
            horScrollView.isHorizontalScrollBarEnabled = false
            txtChemEq.setOnClickListener {
                onItemClickListener?.onClick(it, list[adapterPosition], adapterPosition)
            }
        }
    }

    inner class ChemicalSearcher : Filter () {

        override fun performFiltering(key: CharSequence): FilterResults? {
            if (key.isEmpty()) return null

            val sub_keys : Array<String> = key.toString().toUpperCase().split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            val result = Filter.FilterResults()

            val newList = ArrayList<ChemicalEquation>()

            for (item in defaultList) {
                var isMatched = true
                for (sub_key in sub_keys) {
                    if (!(item.cleanedEquation).toUpperCase().contains(sub_key)) {
                        Log.e("Item is ", item.cleanedEquation + " doesn't contain " + sub_key)
                        isMatched = false
                        break
                    }
                }
                if (isMatched) newList.add(item)
            }

            result.values = newList

            return result

        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            list = if (results?.values != null) {
                results.values as ArrayList<ChemicalEquation>
            } else {
                defaultList
            }
            notifyDataSetChanged()

        }

    }

    interface OnItemClickListener {
        fun onClick(view: View, equation: ChemicalEquation, position: Int)
    }
}
