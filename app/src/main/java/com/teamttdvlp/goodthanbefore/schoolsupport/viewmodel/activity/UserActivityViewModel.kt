package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserManager

class UserActivityViewModel : ViewModel {
    private var mUserManager : UserManager
    constructor() {
        mUserManager = UserManager()
    }
}