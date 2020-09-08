package com.cmexpertise.yogakotlin.models.mvvm_implementors

import com.cmexpertise.yogakotlin.models.api_repositories.LoginVMRepository
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse

interface LoginVMImplementor : BaseImplementor{


    fun retriveLoginData(email : String, password: String,
                         callback: LoginVMRepository.ApiCallBack<LoginResponse>)

}