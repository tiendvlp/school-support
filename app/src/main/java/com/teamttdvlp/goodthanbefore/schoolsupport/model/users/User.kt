package com.teamttdvlp.goodthanbefore.schoolsupport.model.users

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.google.firebase.firestore.Exclude
import java.io.Serializable

class User : Serializable {
    var Id : String = ""
    var DisplayName : String = ""
    var About : String = ""
    var LikedStories : ArrayList<String> = ArrayList()
    var PostedStories : ArrayList<String> = ArrayList()
    var Interests : ArrayList<String> = ArrayList()
    var Bookmarks : ArrayList<String> = ArrayList()
    var Email : String = ""
    var UserFollower : ArrayList<String> = ArrayList()
    var UserFollowing : ArrayList<String> = ArrayList()
    var Avatar : String = ""
    var JointDay : String = ""
//    constructor(parcel: Parcel) : this() {
//        Id = "" + parcel.readString()
//        DisplayName = "" + parcel.readString()
//        Email = "" + parcel.readString()
//        Avatar = "" + parcel.readString()
//        JointDay = "" + parcel.readString()
//        About = "" + parcel.readString()
//        Interests.addAll(parcel.readList())
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(Id)
//        parcel.writeString(DisplayName)
//        parcel.writeString(Email)
//        parcel.writeString(Avatar)
//        parcel.writeString(JointDay)
//        parcel.writeString(About)
//        parcel.writeList(Interests)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<User> {
//        override fun createFromParcel(parcel: Parcel): User {
//            return User(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User?> {
//            return arrayOfNulls(size)
//        }
//    }
}