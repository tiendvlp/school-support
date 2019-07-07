package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

interface IKeepMeLogin {
    fun keepMeLogin () : User?
}