package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import android.net.Uri
import android.util.Log
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginWithCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single

class FirebaseAuthentication : IAuthentication {
    private var mAuth : FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    override fun loginWithEmailAndPassword(email: String, password: String): Single<User> {
        return Single.create { mSingle ->
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
                if (it.isSuccessful) {
                    var user = User()
                    user.Id = it.result!!.user.uid
                    user.DisplayName = it.result!!.user.displayName!!
                    user.Avatar = it.result!!.user.photoUrl.toString()
                    mSingle.onSuccess(user)
                } else {
                    mSingle.onError(it.exception!!)
                }
            })
        }
    }

    override fun loginWithFacebook(token: AccessToken): Single<User> {
        return Single.create { mSingle ->
            val mCredential : AuthCredential = FacebookAuthProvider.getCredential(token.token)
            mAuth.signInWithCredential(mCredential).addOnCompleteListener({
                if (it.isSuccessful) {
                    val result = User()
                    result.Id = it.result!!.user.uid
                    result.Avatar = it.result!!.user!!.photoUrl.toString()
                    result.DisplayName = it.result!!.user!!.displayName!!
                    mSingle.onSuccess(result)
                } else {
                    mSingle.onError(it.exception!!)
                }
            })
        }
    }

    override fun loginWithGoogle(account: GoogleSignInAccount): Single<User> {
        return Single.create { mSingle ->
            val mLoginAction: ILoginWithCredential = LoginWithCredential()
            var mCredential:AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null )
            mAuth.signInWithCredential(mCredential).addOnCompleteListener({
                if (it.isSuccessful) {
                    val result = User()
                    result.Id = it.result!!.user.uid
                    result.Avatar = it.result!!.user!!.photoUrl.toString()
                    result.DisplayName = it.result!!.user!!.displayName!!
                    mSingle.onSuccess(result)
                } else {
                    mSingle.onError(it.exception!!)
                }
            })
        }
    }

    override fun signup(email: String, password: String, displayName: String): Single<User> {
        return Single.create { mSingle ->
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    var user = User()
                    user.DisplayName = displayName
                    user.Id = it.result!!.user.uid
                    val newProfile = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .setPhotoUri(Uri.EMPTY)
                        .build()
                    it.result!!.user.updateProfile(newProfile).addOnCompleteListener {
                        if (it.isSuccessful) {
                            mSingle.onSuccess(user)
                        } else {
                            Log.d("sukien", "new profile failed")
                            mSingle.onError(it.exception!!)
                        }
                    }
                } else {
                    Log.d("sukien", "signup failed: ${it.exception!!.message}")
                    mSingle.onError(it.exception!!)
                }
            }
        }
    }

    override fun keepMeLogin() : Single<User> {
        return Single.create <User> { mSingle ->
            if (mAuth.currentUser != null) {
                var user = User()
                user.Id = mAuth.currentUser!!.uid
                user.DisplayName = mAuth.currentUser!!.displayName!!
                user.Avatar = mAuth.currentUser!!.photoUrl.toString()
                Log.d("KeepMeLogin", user.DisplayName)
                Log.d("KeepMeLogin", user.Avatar)
                mSingle.onSuccess(user)
            } else {
                mSingle.onError(Exception("user 404"))
            }
        }
    }
}