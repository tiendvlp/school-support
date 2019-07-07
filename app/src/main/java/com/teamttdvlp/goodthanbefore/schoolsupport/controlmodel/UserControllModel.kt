package com.teamttdvlp.goodthanbefore.schoolsupport.controlmodel

import android.graphics.drawable.Drawable

class UserControllModel {
    var Id : String = ""
    var DisplayName : String = ""
    var About : String = ""
    var Email : String = ""
    var LikedStories : ArrayList<String> = ArrayList()
    var PostedStories : ArrayList<String> = ArrayList()
    var Interests : ArrayList<String> = ArrayList()
    var Bookmarks : ArrayList<String> = ArrayList()
    var UserFollower : ArrayList<String> = ArrayList()
    var UserFollowing : ArrayList<String> = ArrayList()
    var Avatar : Drawable? = null
    var JointDay : String = ""
}