package com.cmexpertise.yogakotlin.models.api_repositories

import android.util.Log
import com.cmexpertise.yogakotlin.models.mvvm_implementors.UserVMImplementor
import com.cmexpertise.yogakotlin.models.network_data.ApiClient
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UsersDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListingVMRepository : UserVMImplementor{

    private var userApicall : Call<UsersDataResponse>? = null

    interface ApiCallBack<T>{
        fun onSuccess(responseData : UsersDataResponse?)
        fun onError(message : String?)
    }

    override fun retriveUserData(callback: ApiCallBack<UsersDataResponse>) {
        userApicall = ApiClient.build()?.callUserListApi()

        userApicall?.enqueue(object : Callback<UsersDataResponse>{
            override fun onFailure(call: Call<UsersDataResponse>?, t: Throwable?) {
                callback.onError(t?.message)
            }

            override fun onResponse(call: Call<UsersDataResponse>?, response: Response<UsersDataResponse>?) {
                response?.body()?.let {baseResponse ->
                    if(response.isSuccessful){
                        callback.onSuccess(baseResponse)
                        Log.d("login_log", "success2")
                    }else{
                        callback.onError("Error")
                        Log.d("login_log", "Err2 ")
                    }
                }
            }

        })
    }

    override fun cancel() {
          userApicall?.cancel()
    }
}