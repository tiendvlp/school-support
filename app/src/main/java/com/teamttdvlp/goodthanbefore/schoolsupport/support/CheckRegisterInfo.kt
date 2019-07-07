package com.teamttdvlp.goodthanbefore.schoolsupport.support

class CheckRegisterInfo {
    fun checkEmail (email:String):Boolean {
        var regex = Regex(".+[@].+[\\.].+")
        return email.matches(regex)
    }

    fun checkPassword (password:String):Boolean {
        var regexContainNumber = Regex(".*[0-9].*")
        var regexContainUppercase = Regex(".*[A-Z].*")
        return password.matches(regexContainNumber) && password.matches(regexContainUppercase) && password.length > 8
    }

    fun checkDisplayName(displayName:String) : Boolean {
        return displayName.length>=8
    }

}