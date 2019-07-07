package com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalequation

import android.os.Parcel
import android.os.Parcelable
import com.teamttdvlp.goodthanbefore.schoolsupport.support.HtmlContentGetter

class ChemicalEquation (
    var id : Int = 0,
    var equation : String = "",
    var condition : String = "",
    var howToDo : String = "",
    var phenonema : String = "") : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (other is ChemicalEquation) {
            if ((equation == other?.equation)
                && (condition == other.condition)
                && (howToDo == other.howToDo)
                && (phenonema == other.phenonema)) {
                return true
            }
        }
        return false
    }

    var cleanedEquation : String = HtmlContentGetter.getContent(equation)

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        cleanedEquation = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(equation)
        parcel.writeString(condition)
        parcel.writeString(howToDo)
        parcel.writeString(phenonema)
        parcel.writeString(cleanedEquation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChemicalEquation> {
        override fun createFromParcel(parcel: Parcel): ChemicalEquation {
            return ChemicalEquation(parcel)
        }

        override fun newArray(size: Int): Array<ChemicalEquation?> {
            return arrayOfNulls(size)
        }
    }

}