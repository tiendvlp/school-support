package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SetUserInterestEvent
import io.reactivex.Single

class UserInterestDb : IUserInterestDb {
    private var mFirebaseFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun getUserInterest(userId: String): Single<ArrayList<String>> {
        return Single.create {mSingle ->
        mFirebaseFirestore.collection("Users")
            .document(userId)
            .collection("StaticData")
            .document("Interests")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    var data : List<String> = if(it.result?.get("interests") != null)
                        it.result!!.get("interests") as List<String> else ArrayList<String>()
                    var result = ArrayList<String>()
                    result.addAll(data)
                    mSingle.onSuccess(result!!)
                } else {
                    mSingle.onError(it.exception!!)
                }
            }
        }
    }

    override fun addUserInterest(userId: String, interests: ArrayList<String>, callback: SetUserInterestEvent) {
        val data : HashMap<String, ArrayList<String>> = HashMap()
        data["interests"] = interests
        mFirebaseFirestore.collection("Users")
            .document(userId)
            .collection("StaticData")
            .document("Interests")
            .set(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback.onSetUserInterestEventSuccess()
                } else {
                    callback.onSetUserInterestEventFailed(it.exception)
                }

            }
    }

}