package com.teamttdvlp.goodthanbefore.schoolsupport.model.Responsitory

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class UserResponsitory {
    fun abc () {
        var a = FirebaseFirestore.getInstance().collection("Stories")
            .document("94kdg7Xs7L6SZKL1INag")
        FirebaseFirestore.getInstance().runTransaction {
//            it.update(a, hashMapOf(
//                Pair<String, Any>("authorDisplayName","tiendangminh123"),
//                Pair<String, Any>("authorDisplayName2","tiendangminh123333")))
            it.update(a,"authorDisplayName", "tienasbdbsja")
        }.addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("Yayyy", "thanh cong")
            } else {
                Log.d("That bai", it.exception!!.message)


            }
        }
    }
}