package com.cmexpertise.yogakotlin.models.api_repositories

import android.util.Log
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor
import com.cmexpertise.yogakotlin.models.network_data.ApiClient
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginVMRepository : LoginVMImplementor{

    private var loginApicall : Call<LoginResponse>? = null

    interface ApiCallBack<T>{
        fun onSuccess(responseData : LoginResponse?)
        fun onError(message : String?)
    }

    override fun retriveLoginData(email : String, password: String,
                                  callback: ApiCallBack<LoginResponse>) {

        loginApicall = ApiClient.build()?.callLoginApi(email,password)

        loginApicall?.enqueue(object : Callback<LoginResponse>{
             override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                 callback.onError(t?.message)
             }

             override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
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
          loginApicall?.cancel()
    }
}