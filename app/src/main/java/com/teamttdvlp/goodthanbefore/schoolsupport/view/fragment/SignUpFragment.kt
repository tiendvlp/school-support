package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.SignUpFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.InterestActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.LoginActivityViewModel
import java.lang.Exception

class SignUpFragment : Fragment(), LoginEvent {

    private lateinit var mBind : SignUpFragmentBinding
    private lateinit var activityModel : LoginActivityViewModel

    private var isEmailValid = false
    private var isPasswordValid = true
    private var isRepeatPassValidValid = true
    private var isDisplayNameValid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBind = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_sign_up, container, false)
        activityModel = activity!!.getViewModel()
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    private fun addEvents() {
        mBind.btnSignupOk.setOnClickListener{
            val email = mBind.edtEmail.text.toString()
            val password = mBind.edtPassword.text.toString()
            val displayName = mBind.edtDisplayName.text.toString()
//            activityModel.signup(email, password, displayName)
            activityModel.isLoading.value = View.GONE
        }

        mBind.edtEmail.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                isEmailValid = activityModel.checkEmail(s.toString())
                mBind.rbtnEmailValid.isChecked = isEmailValid
                mBind.rbtnEmailValid.visibility = View.VISIBLE
                resetButton()
                if (!isEmailValid) {
                    mBind.txtErrMessEmailInvalid.visibility = View.VISIBLE
                } else {
                    mBind.txtErrMessEmailInvalid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        mBind.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isPasswordValid = activityModel.checkPassword(s.toString())
                mBind.rbtnPasswordValid.isChecked = isPasswordValid
                mBind.rbtnPasswordValid.visibility = View.VISIBLE
                resetButton()
                if (!isPasswordValid) {
                    mBind.txtErrMessPasswordInvalid.visibility = View.VISIBLE
                } else {
                    mBind.txtErrMessPasswordInvalid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        mBind.edtDisplayName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var isDisplayNameValid = activityModel.checkDisplayname(s.toString())
                mBind.rbtnDisplayNameValid.isChecked = isDisplayNameValid
                mBind.rbtnDisplayNameValid.visibility = View.VISIBLE
                resetButton()
                if (!isDisplayNameValid) {
                    mBind.txtErrMessDisplayNameInvalid.visibility = View.VISIBLE
                } else {
                    mBind.txtErrMessDisplayNameInvalid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        mBind.edtRepeatPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isRepeatPassValidValid = mBind.edtPassword.text.toString().equals(mBind.edtRepeatPassword.text.toString())
                mBind.rbtnRepeatPasswordValid.isChecked = isRepeatPassValidValid
                mBind.rbtnRepeatPasswordValid.visibility = View.VISIBLE
                resetButton()
                if (!isRepeatPassValidValid) {
                    mBind.txtErrMessRepeatPasswordInValid.visibility = View.VISIBLE
                } else {
                    mBind.txtErrMessRepeatPasswordInValid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


    }

    override fun onLoginSuccess(user: User) {
        activityModel.isLoading.value = View.VISIBLE
        var intent = Intent(context, InterestActivity::class.java)
        CurrentUser.currentUser = user
        startActivity(intent)
        activity?.finish()
    }

    override fun onLoginFailed(e: Exception?) {
        Log.e("faled login", "adisconme")
        activityModel.isLoading.value = View.VISIBLE
    }


    private fun resetButton () {
        if (isEmailValid && isDisplayNameValid && isPasswordValid && isRepeatPassValidValid) {
            mBind.btnSignUpDefault.visibility = View.GONE
            mBind.btnSignupOk.visibility = View.VISIBLE
        } else {
            mBind.btnSignUpDefault.visibility = View.VISIBLE
            mBind.btnSignupOk.visibility = View.GONE
        }
    }

    private fun addControls() {
    }

    companion object {
        private val mInstance : SignUpFragment = SignUpFragment()
        fun getInstance () : SignUpFragment {
            return mInstance
        }

    }
}