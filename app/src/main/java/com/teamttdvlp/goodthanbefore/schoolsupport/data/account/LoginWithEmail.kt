package com.teamttdvlp.goodthanbefore.schoolsupport.data.account

import com.google.firebase.auth.FirebaseAuth
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory.ILoginWithEmail
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single

class LoginWithEmail : ILoginWithEmail {
    private var mAuth:FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    override fun login(email: String, password: String) : Single<User> {
        return Single.create<User> { single ->
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
                if (it.isSuccessful) {
                    var user = User()
                    user.Id = it.result!!.user.uid
                    user.DisplayName = it.result!!.user.displayName!!
                    user.Avatar = it.result!!.user.photoUrl.toString()
                    single.onSuccess(user)
                } else {
                    single.onError(it.exception!!)
                }
            })
        }
    }
}

