package com.teamttdvlp.goodthanbefore.schoolsupport.support

inline fun <T> ArrayList<T>.add_removeIdenticalItem (partner:ArrayList<T>) : ArrayList<T> {
    for (item1 in this) {
        for (item2 in partner) {
            if (item1 == item2) {
                partner.remove(item2)
            }
        }
}
    this.addAll(partner)
    return this
}