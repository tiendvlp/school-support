package com.teamttdvlp.goodthanbefore.schoolsupport.domain.detail.responsitory

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Completable
import io.reactivex.Single

interface IUserResponsitory {
    fun getUser (id:String) : Single<User>

    fun writeUser(user: User): Completable
}