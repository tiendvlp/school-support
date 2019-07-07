package com.teamttdvlp.goodthanbefore.schoolsupport.model.functions

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.IInterestDownload
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import java.lang.Exception

class InterestDownload (var app : Application) : IInterestDownload {

    val firestoreDB = FirebaseFirestore.getInstance()

    val storageDB = FirebaseStorage.getInstance()

    override fun loadInterest(onLoadSuccess : (interst : ArrayList<Interest>) -> Unit
                     ,onGetCollectionFailed: (Exception) -> Unit ) {

        firestoreDB.collection("Topic").get().
            addOnSuccessListener {
                val result : ArrayList<Interest> = ArrayList()
                for (doc in it.documents) {
                    val interest = Interest(doc["Avatar"].toString(), doc.id, doc["Description"].toString(), false)
                    result.add(interest)
                }
                onLoadSuccess(result)
            }.
            addOnFailureListener {
                it.printStackTrace()
                onGetCollectionFailed(it)
            }
    }

}