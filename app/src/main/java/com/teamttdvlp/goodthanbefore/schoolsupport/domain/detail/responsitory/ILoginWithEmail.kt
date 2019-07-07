package com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single

interface ILoginWithEmail {
    fun login (email:String, password:String) : Single<User>
}