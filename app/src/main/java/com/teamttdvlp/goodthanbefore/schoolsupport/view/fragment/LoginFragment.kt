package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.LoginFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.LogCode
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.InterestActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.MainActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.OfflineToolActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.LoginActivityViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

class LoginFragment : Fragment(), GoogleApiClient.OnConnectionFailedListener, FacebookCallback<LoginResult>, LoginEvent {
    private lateinit var mBinding : LoginFragmentBinding
    private lateinit var signinApi : GoogleApiClient
    private lateinit var mCallbackManager: CallbackManager
    private var permissonFacebook = Arrays.asList("email","public_profile")
    private lateinit var mFBLoginManager:com.facebook.login.LoginManager
    private val REQUESTCODE_GG_SIGNIN = 9

    private lateinit var activityViewModel : LoginActivityViewModel
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        addControls()
        setUp()
        addEvents()
        activityViewModel.isLoading.value = View.GONE
//        if (!activityViewModel.keepMeLogin()) {
            activityViewModel.isLoading.value = View.VISIBLE
//        }
        return mBinding.root
    }

    private fun addControls() {
        activityViewModel = activity!!.getViewModel()
    }

    private fun addEvents() {
        mBinding.btnLogin.setOnClickListener {
//            activityViewModel.isLoading.value =  View.GONE
            val email = mBinding.edtEmail.text.toString()
            val password = mBinding.edtPassword.text.toString()
            activityViewModel.loginNormally(email, password).subscribe ({ user ->
                Toast.makeText(this.context, "Dang nhap thanh cong ${user.Email} $user.id",  Toast.LENGTH_LONG).show()
            },
                {e ->
                    Log.d("KiemThuDangNhap", "that bai ${e.message}")
            })
        }

        mBinding.btnLoginGoogle.setOnClickListener {
            activityViewModel.isLoading.value =  View.GONE
            var intent = Auth.GoogleSignInApi.getSignInIntent(signinApi)
            startActivityForResult(intent, REQUESTCODE_GG_SIGNIN)
        }

        mBinding.btnLoginFacebook.setOnClickListener {
            activityViewModel.isLoading.value = View.GONE
            loginFacebook()
        }

        mBinding.btnWorkOffline.setOnClickListener {
            startActivity(Intent(context, OfflineToolActivity::class.java))
        }

        mBinding.btnSignup.setOnClickListener {
            if (context is OnButtonsClickListener) {
                (context as OnButtonsClickListener).onSignUpButtonClick()
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE_GG_SIGNIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
//                activityViewModel.loginWithGoogle(account!!)
            } catch (e: ApiException) {
                Result
                Log.w(LogCode.login, "Google sign in failed", e)
            }
        } else {
        }


    }

    private fun GGLoginSetup() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .requestProfile()
            .build()

        signinApi = GoogleApiClient.Builder(context!!)
            .enableAutoManage(requireActivity(), this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
    }

    private fun setupLoginFacebook () {
        FacebookSdk.sdkInitialize(context)
        mCallbackManager = CallbackManager.Factory.create()
        mFBLoginManager = com.facebook.login.LoginManager.getInstance()
    }

    private fun loginFacebook () {
        mFBLoginManager.logInWithReadPermissions(this, permissonFacebook)
        mFBLoginManager.registerCallback(mCallbackManager, this)
    }

    // google login connection failed
    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.w(LogCode.login, "GG connection failed: " + p0.errorMessage)
    }

    override fun onSuccess(result: LoginResult?) {
//        activityViewModel.loginWithFacebook(result!!.accessToken)
    }

    override fun onCancel() {
        Log.w(LogCode.login, "FB connection was cancel")
    }
    private  var mContext : Context? = null

    override fun onLoginSuccess(user: User) {
        Log.d("LoginCC", user.Interests.size.toString())
        if (user.Interests.size == 0) {
            var intent = Intent(activity!!.applicationContext, InterestActivity::class.java)
                intent.putExtra("User", user)
                startActivity(intent)
        } else {
            Log.d("test", "chay")
            var intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        CurrentUser.currentUser = user
        activity?.finish()
        Toast.makeText(context, "Login success", Toast.LENGTH_LONG)
    }

    override fun onLoginFailed(e: Exception?) {
        activityViewModel.isLoading.value =  View.VISIBLE
        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG)
    }

    override fun onError(error: FacebookException?) {
        Log.w(LogCode.login, "FB connection failed: " + error!!.message)
    }

    private fun setUp() {
        GGLoginSetup()
        setupLoginFacebook()
        activityViewModel.onLoginEvent = this
    }
    companion object {
        private val mInstance : LoginFragment = LoginFragment()
        fun getInstance () : LoginFragment {
            return mInstance
        }
    }

    interface OnButtonsClickListener {

        fun onSignUpButtonClick ()

    }
}
