package com.cmexpertise.yogakotlin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmexpertise.yogakotlin.models.api_repositories.LoginVMRepository
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse

class LoginViewModel(private val loginVMImplementor: LoginVMImplementor) : ViewModel() {

    private val _liveLoginResponse = MutableLiveData<LoginResponse>()
    val loginResonseData : LiveData<LoginResponse> = _liveLoginResponse

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<String>()
    val onMessageError:LiveData<String> = _onMessageError



    fun retiveLoginData(email : String, pwd: String){

        _isViewLoading.postValue(true)
            loginVMImplementor.retriveLoginData(email, pwd ,object : LoginVMRepository.ApiCallBack<LoginResponse>{
                override fun onSuccess(responseData: LoginResponse?) {
                    Log.d("login_log","Success")

                    _isViewLoading.postValue(false)
                    _liveLoginResponse.postValue(responseData)
                }

                override fun onError(message: String?) {
                    Log.d("login_log","failue "+message)
                    _isViewLoading.postValue(false)
                    _onMessageError.postValue(message)
                }

            })




    }

}