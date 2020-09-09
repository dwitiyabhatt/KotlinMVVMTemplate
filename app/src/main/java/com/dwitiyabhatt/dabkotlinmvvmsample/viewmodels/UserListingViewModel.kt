package com.cmexpertise.yogakotlin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmexpertise.yogakotlin.models.api_repositories.LoginVMRepository
import com.cmexpertise.yogakotlin.models.api_repositories.UserListingVMRepository
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor
import com.cmexpertise.yogakotlin.models.mvvm_implementors.UserVMImplementor
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UserDetails
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UsersDataResponse

class UserListingViewModel(private val userVMImplementor: UserVMImplementor) : ViewModel() {

    private val _liveUserListingResponse = MutableLiveData<List<UserDetails>>()
    val loginResonseData : LiveData<List<UserDetails>> = _liveUserListingResponse

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<String>()
    val onMessageError:LiveData<String> = _onMessageError



    fun retriveUserData(){
        _isViewLoading.postValue(true)
        userVMImplementor.retriveUserData(object : UserListingVMRepository.ApiCallBack<UsersDataResponse>{
                override fun onSuccess(responseData: UsersDataResponse?) {
                    Log.d("login_log","Success")

                    _isViewLoading.postValue(false)
                    _liveUserListingResponse.postValue(responseData?.data)
                }

                override fun onError(message: String?) {
                    Log.d("login_log","failue "+message)
                    _isViewLoading.postValue(false)
                    _onMessageError.postValue(message)
                }

            })




    }

}