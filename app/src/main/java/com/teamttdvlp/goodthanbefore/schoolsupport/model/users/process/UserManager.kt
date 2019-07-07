package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserInterest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserInterestEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteUserInterestEvent
import java.lang.Exception

class UserManager : ReadInfoEvent, WriteInfoEvent,

                    GetUserInterestEvent, WriteUserInterestEvent{

    private var mUserInfo : IUserInfo
    private var mUserInterest : IUserInterest

     var getUserInterestListener : GetUserInterestEvent? = null
     var getUserInfoListener: ReadInfoEvent? = null
     var setUserInfoListener : WriteInfoEvent? = null

    constructor() {
        mUserInfo = UserInfo()
        mUserInterest = UserInterest()
    }

    fun getInterests (userId:String) {
        mUserInterest.getUserInterest(userId, this)
    }

    fun getInfo (userId:String) {
        mUserInfo.readInfo(userId, this)
    }

    fun writeInfo (user:User) {
        mUserInfo.writeInfo(user, this)
    }

    override fun onWriteUserInterestSuccess() {

    }

    override fun onWriteUserInterestFailed(e: Exception?) {

    }

    override fun onReadInfoSuccess(user: User?) {
        if (getUserInfoListener != null) {
            getUserInfoListener!!.onReadInfoSuccess(user)
        }
    }

    override fun onReadInfoFailed(e: Exception?) {
        if (getUserInfoListener != null) {
            getUserInfoListener!!.onReadInfoFailed(e)
        }
    }

    override fun onGetUserInterestSuccess(result: ArrayList<String>) {
        if(getUserInterestListener != null) {
            getUserInterestListener!!.onGetUserInterestSuccess(result)
        }
    }

    override fun onWriteInfoSuccess() {
        if(setUserInfoListener != null) {
            setUserInfoListener!!.onWriteInfoSuccess()
        }
    }

    override fun onWriteInfoFailed(e: Exception?) {
        if (setUserInfoListener != null) {
            setUserInfoListener!!.onWriteInfoFailed(e)
        }
    }

    override fun onGetUserInterestFailed(e: Exception?) {
        if (getUserInterestListener != null) {
            getUserInterestListener!!.onGetUserInterestFailed(e)
        }
    }
}