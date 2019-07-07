package com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement

import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement

class FamilyChemicalElement (chemicalElement : String ): IChemicalElement {
    var chemicalElementName : String = chemicalElement
        set (newValue) {
            field = "Họ $newValue"
        }
}