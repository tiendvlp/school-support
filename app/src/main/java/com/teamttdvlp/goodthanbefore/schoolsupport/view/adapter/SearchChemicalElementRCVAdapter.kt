package com.example.retroschoolsupporttoolmodule


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement

import java.util.ArrayList

class SearchChemicalElementRCVAdapter(internal var context: Context) : RecyclerView.Adapter<SearchChemicalElementRCVAdapter.DataViewHolder>(), Filterable {

    internal var filter: SearchChemicalElementRCVAdapter.Searcher

    internal lateinit var list: ArrayList<ChemicalElement>

    internal lateinit var defaultList: ArrayList<ChemicalElement>

    internal var onCustomItemClickListener: SearchChemicalElementRCVAdapter.OnItemClickListener? = null

    private var rcv: RecyclerView? = null

    private val onClickListener = View.OnClickListener { v ->
        if (onCustomItemClickListener != null) {
            val position = rcv!!.indexOfChild(v)
            onCustomItemClickListener!!.onItemClick(list[position])
        }
    }

    init {
        filter = Searcher()
    }

    fun adaptFor(rcv: RecyclerView) {
        this.rcv = rcv
        rcv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rcv.adapter = this
    }

    fun setData(datas: Collection<ChemicalElement>) {
        list = ArrayList()
        defaultList = ArrayList()
        list.addAll(datas)
        defaultList.addAll(list)
        notifyDataSetChanged()
    }

    fun observe(editText : EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getFilter().filter(s)
            }

        })
    }

    fun setOnItemClickListener(onCustomItemClickListener: OnItemClickListener) {
        this.onCustomItemClickListener = onCustomItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchChemicalElementRCVAdapter.DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.child_search_chemical_element, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchChemicalElementRCVAdapter.DataViewHolder, position: Int) {
        holder.itemView.setOnClickListener(onClickListener)
        val element = list[position]
        holder.txt_chem_element_symbol.text = element.symbol
        holder.txt_chem_element_name.text = element.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        return filter
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txt_chem_element_symbol: TextView
        internal var txt_chem_element_name: TextView

        init {
            txt_chem_element_name = itemView.findViewById(R.id.txt_chem_element_name)
            txt_chem_element_symbol = itemView.findViewById(R.id.txt_chem_element_symbol)
        }
    }

    inner class Searcher : Filter() {


        override fun performFiltering(key: CharSequence): Filter.FilterResults? {

            if (key.length == 0) return null

            val result = Filter.FilterResults()

            val newList = ArrayList<ChemicalElement>()

            for (element in defaultList) {
                if ((element.symbol + element.name).toUpperCase().contains(key.toString().toUpperCase())) {
                    newList.add(element)
                }
            }


            result.values = newList

            return result
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults?) {

            if (results != null && results.values != null) {
                list = results.values as ArrayList<ChemicalElement>
            } else {
                list = defaultList
            }

            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(element: ChemicalElement)
    }
}
