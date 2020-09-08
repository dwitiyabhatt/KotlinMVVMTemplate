package com.cmexpertise.yogakotlin.views.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cmexpertise.yogakotlin.models.injectors.LoginInjection
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse
import com.cmexpertise.yogakotlin.utils.AppConstants
import com.cmexpertise.yogakotlin.utils.CommonMethods
import com.cmexpertise.yogakotlin.viewmodels.LoginViewModel
import com.dwitiyabhatt.dabkotlinmvvmsample.R
import com.dwitiyabhatt.dabkotlinmvvmsample.SampleAplication
import com.dwitiyabhatt.dabkotlinmvvmsample.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setBinding()
        initializeViewMOdel()
        registerClickListeners()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun registerClickListeners() {
        btnLogin.setOnClickListener(View.OnClickListener {
            Log.d("login_log", "method called")
            if(validateInput() && CommonMethods.checkInterNetConnection(this)){
                loginViewModel.retiveLoginData(""+getTrimmedText(binding.edUserName), ""+getTrimmedText(binding.edPassword))

                //SampleAplication.getmInstance()?.savePreferenceDataString(AppConstants.PARAM_MOBILE_NUMBER, fullMobileNumber)

            }

        })
    }

    private fun validateInput() : Boolean{
        if(getTrimmedText(binding.edUserName).isNullOrBlank()){
            showSnackbar(binding.root,getString(R.string.please_enter_email))
            return false
        } else if(getTrimmedText(binding.edPassword).isNullOrBlank()){
            showSnackbar(binding.root,getString(R.string.please_enter_password))
            return false
        }

        return  true
    }

    private fun initializeViewMOdel() {
        loginViewModel = ViewModelProvider(
            this,
            LoginInjection.provideViewModelFactory()
        ).get(LoginViewModel::class.java)

       loginViewModel.loginResonseData.observe(this,loginDataObserver)
        loginViewModel.isViewLoading.observe(this,isViewLoadingObserver)
        loginViewModel.onMessageError.observe(this, errorMessageObserver)
    }

    private val loginDataObserver = Observer<LoginResponse>{ loginResponseData ->
        showToast("Got data")
        CommonMethods.showLog("login_log","Data "+loginResponseData.toString())
        SampleAplication.getmInstance()?.savePreferenceDataString(AppConstants.PARAM_TOKEN, loginResponseData.token)

    }

    private val isViewLoadingObserver = Observer<Boolean>{isLoading ->
        if(isLoading) showHideProgress(true,binding.layoutProgress.frProgress)
        else showHideProgress(false,binding.layoutProgress.frProgress)

    }

    private val errorMessageObserver = Observer<String>{message ->
        showToast(message)

    }


}