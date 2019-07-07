package com.example.retroschoolsupporttoolmodule

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.*

const val EMPTY = 0
const val ELEMENT = 1
const val TITLE = 2

@Suppress("DEPRECATION")
class BangTuanHoangGridviewAdapter (var context: Context, var itemList : ArrayList<IChemicalElement>): BaseAdapter() {

    var layoutInflater : LayoutInflater = LayoutInflater.from(context)

    private var onItemClick : OnItemClickListener? = null

    private var gridView : GridView? = null

    private var onItemClickListener : (View) -> Unit = {
        var item = itemList[gridView!!.indexOfChild(it)]
        if (item is ChemicalElement) onItemClick?.onClick(item)
    }

    fun adaptFor (target : GridView) {
        gridView = target
        gridView!!.adapter = this
    }

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        var item = itemList.get(pos)
        return when (item) {
            is ChemicalElement -> fromElementToChildView(item, parent!!)
            is ShortyTitleChemicalElement -> processShortyTitle(item, parent!!)
            is TitleChemicalElement -> processTitle(item, parent!!)
            is CycleChemicalElement -> processCycleChemicalElement(item, parent!!)
            is EmptyChemicalElement -> processEmptyElement(parent!!)
            is CycleAndGroupElement -> processCycleAndGroupElement(parent!!)
            is FamilyChemicalElement -> processFamilyChemicalElement(item, parent!!)
            else -> processEmptySpace(parent!!)
        }
    }

    fun fromElementToChildView (element : ChemicalElement, parent : ViewGroup) : View {
        var childView = layoutInflater.inflate(R.layout.item_bangtuanhoang_element, parent, false)
        childView.background = element.background
        var txtSymbol = childView.findViewById<TextView>(R.id.item_chem_symbol)
        var txtName = childView.findViewById<TextView>(R.id.item_chem_txt_name)
        var txtMass = childView.findViewById<TextView>(R.id.item_chem_mass)
        var txtElecConfig = childView.findViewById<TextView>(R.id.item_chem_txt_electron_config)
        var txtEleNegative= childView.findViewById<TextView>(R.id.item_chem_ele_negative)
        var txtOxydationNumber = childView.findViewById<TextView>(R.id.item_chem_oxydation_numbers)
        var txtPosition = childView.findViewById<TextView>(R.id.item_chem_position)

        txtPosition.text = element.elecCount.toString()
        txtSymbol.text = element.symbol
        txtName.text = element.name
        txtMass.text = element.mass
        txtElecConfig.text = processEletronConfigure(element.eleFigure)
        txtEleNegative.text = element.eleNegative
        txtOxydationNumber.text = element.oxydationNumbers
        childView.setOnClickListener(onItemClickListener)
        return childView
    }

    fun processEmptyElement (parent : ViewGroup) : View {
        var childView = layoutInflater.inflate(R.layout.item_empty_bangtuanhoang_element, parent, false)
        return childView
    }

    fun processEmptySpace(parent : ViewGroup) : View {
        var childView = layoutInflater.inflate(R.layout.item_empty_space_bangtuanhoang, parent, false)
        return childView
    }

    fun processTitle (title : TitleChemicalElement, parent : ViewGroup) : View{
        var childView = layoutInflater.inflate(R.layout.item_title_bangtuanhoang_element, parent, false)
        var imgTitle = childView.findViewById<ImageView>(R.id.img_title)
        imgTitle.background = context.getDrawable(title.titleImageId)
        return childView
    }

    fun processShortyTitle (title : ShortyTitleChemicalElement, parent : ViewGroup) : View{
        var childView = layoutInflater.inflate(R.layout.item_short_title_bangtuanhoang_element, parent, false)
        var imgTitle = childView.findViewById<ImageView>(R.id.img_title)
        imgTitle.background = context.getDrawable(title.titleImageId)
        return childView
    }

    fun processCycleAndGroupElement (parent : ViewGroup) : View{
        var childView = layoutInflater.inflate(R.layout.item_nhom_chuky_bangtuanhoang_element, parent, false)
        var imgTitle = childView.findViewById<ImageView>(R.id.img_title)
        imgTitle.background = context.getDrawable(R.drawable.nhom_chuky)
        return childView
    }

    fun processCycleChemicalElement (cycle : CycleChemicalElement, parent : ViewGroup) : View {
        var childView = layoutInflater.inflate(R.layout.item_chuky_bangtuanhoang_element, parent, false)
        var imgTitle = childView.findViewById<ImageView>(R.id.img_title)
        imgTitle.background = context.getDrawable(cycle.cycleImageId)
        return childView
    }

    fun processFamilyChemicalElement (chemicalElement : FamilyChemicalElement, parent : ViewGroup) : View{
        var childView = layoutInflater.inflate(R.layout.item_ho_chemical_element, parent, false)
        var title = childView.findViewById<TextView>(R.id.txt_ho_chemical_element)
        title.text = chemicalElement.chemicalElementName
        return childView
    }

    fun processEletronConfigure (eleConfig : String) : CharSequence{
        var result = ""
        var a : MutableList<String> = eleConfig.split(".") as MutableList<String>

        for (e in a) {
            if (!e.contains("[") && !e.contains("]"))
            {
                e.substring(0, 2) + "<sup>" + e.substring(2) + "</sup>"
                result += e.removeRange(2, e.length).plus("<sup>" + e.substring(2) + "</sup>")
            } else {
                result += e
            }
        }

        return Html.fromHtml(result)
    }

    override fun getItemId(pos : Int): Long = pos.toLong()

    override fun getCount(): Int = itemList.size

    override fun getItem(pos: Int): Any = itemList[pos]

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    fun setOnItemClickListener (onItemClick : OnItemClickListener) {
        this.onItemClick = onItemClick
    }

    fun setOnItemClickListener (onItemClick : (ChemicalElement) -> Unit) {
        this.onItemClick = object : OnItemClickListener {
            override fun onClick(item : ChemicalElement) {
                onItemClick(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick (item : ChemicalElement)
    }
}