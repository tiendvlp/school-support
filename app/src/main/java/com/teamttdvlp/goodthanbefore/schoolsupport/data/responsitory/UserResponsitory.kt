package com.teamttdvlp.goodthanbefore.schoolsupport.data.responsitory

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ICmdDepotProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.IUserResponsitory
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Completable
import io.reactivex.Single

class UserResponsitory : IUserResponsitory {
    private var mFirestore : FirebaseFirestore
    private var mCmdDepot : ICmdDepotProvider

    constructor(cmd : ICmdDepotProvider, firestore: FirebaseFirestore) {
        mFirestore = firestore
        mCmdDepot = cmd
    }

    override fun getUser(id: String): Single<User> {
        var singleObs =  Single.create<User> {single ->
        mFirestore.collection("Users").document(id).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result : User = it.getResult()!!.toObject(User::class.java)!!
                    single.onSuccess(result)
                } else {
                    single.onError(it.exception!!)
                }
            }
        }
        return singleObs
    }

    override fun writeUser(user: User): Completable {
        return Completable.create { completable ->
            mFirestore.collection("Users").document(user.Id).set(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val newProfile = UserProfileChangeRequest.Builder()
                            .setDisplayName(user.DisplayName)
                            .setPhotoUri(Uri.parse(user.Avatar))
                            .build()
                        FirebaseAuth.getInstance().currentUser?.updateProfile(newProfile)?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                completable.onComplete()
                            } else {
                                Log.d("sukien", "new profile failed")
                                completable.onError(it.exception!!)
                            }
                        }

                    } else {
                        completable.onError(it.exception!!)
                    }
                }
        }
    }
}