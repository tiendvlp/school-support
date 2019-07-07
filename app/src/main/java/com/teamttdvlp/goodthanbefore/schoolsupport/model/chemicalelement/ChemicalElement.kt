package com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement
import java.io.Serializable

data class ChemicalElement (
    var background : Drawable? = null,
    var name : String,
    var symbol : String,
    var elecCount : Int,
    var notron_count : Int,
    var mass : String,
    var eleFigure : String,
    var inReality : String,
    var eleNegative : String,
    var oxydationNumbers : String? = null) : IChemicalElement, Parcelable {

    constructor(parcel: Parcel) : this(
        null,
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeInt(elecCount)
        parcel.writeInt(notron_count)
        parcel.writeString(mass)
        parcel.writeString(eleFigure)
        parcel.writeString(inReality)
        parcel.writeString(eleNegative)
        parcel.writeString(oxydationNumbers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChemicalElement> {
        override fun createFromParcel(parcel: Parcel): ChemicalElement {
            return ChemicalElement(parcel)
        }

        override fun newArray(size: Int): Array<ChemicalElement?> {
            return arrayOfNulls(size)
        }
    }
}