package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import android.app.Application
import androidx.lifecycle.MutableLiveData
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import com.teamttdvlp.goodthanbefore.schoolsupport.model.graph.process.GraphDrawerDataGetter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.GraphDrawerActivity
class GraphDrawerActivityViewModel (var view : Application) {

    var ldt_graph_type : MutableLiveData<GraphInfo> = MutableLiveData()

    fun loadData (activity : GraphDrawerActivity) {
        ldt_graph_type.value = GraphDrawerDataGetter(activity).data
    }
}

data class GraphInfo (

        var startX : Float = 0f,

        var endX : Float = 0f,

        var color : Int = Color.BLACK,

        var calc_content : String = "",

        // Contain variable
        var graph_info : FloatArray?,

        var graph_type : Int ) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readString(),
            parcel.createFloatArray(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(startX)
        parcel.writeFloat(endX)
        parcel.writeInt(color)
        parcel.writeString(calc_content)
        parcel.writeFloatArray(graph_info)
        parcel.writeInt(graph_type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GraphInfo> {
        override fun createFromParcel(parcel: Parcel): GraphInfo {
            return GraphInfo(parcel)
        }

        override fun newArray(size: Int): Array<GraphInfo?> {
            return arrayOfNulls(size)
        }
    }
}