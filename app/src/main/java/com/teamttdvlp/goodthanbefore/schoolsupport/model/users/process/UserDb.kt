package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import io.reactivex.Single

class UserDb : IUserDb {
    private var mFirestoreRef : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun addUser(user: User, callback: WriteInfoEvent) {
        mFirestoreRef.collection("Users").document(user.Id).set(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val newProfile = UserProfileChangeRequest.Builder()
                        .setDisplayName(user.DisplayName)
                        .setPhotoUri(Uri.parse(user.Avatar))
                        .build()
                    FirebaseAuth.getInstance().currentUser?.updateProfile(newProfile)?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            callback.onWriteInfoSuccess()
                        } else {
                            Log.d("sukien", "new profile failed")
                            callback.onWriteInfoFailed(it.exception)
                        }
                    }

                } else {
                    callback.onWriteInfoFailed(it.exception)
                }
            }
    }

    override fun getUser(userId: String): Single<User> {
        return Single.create { mSingle ->
            Log.d("getUser", userId)
            mFirestoreRef.collection("Users").document(userId).get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val result : User? = it.getResult()!!.toObject(User::class.java)
                        mSingle.onSuccess(result!!)
                    } else {
                        mSingle.onError(it.exception!!)
                    }
                }
             }
        }
}