package com.cmexpertise.yogakotlin.models.mvvm_implementors

import com.cmexpertise.yogakotlin.models.api_repositories.LoginVMRepository
import com.cmexpertise.yogakotlin.models.api_repositories.UserListingVMRepository
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UsersDataResponse

interface UserVMImplementor : BaseImplementor{
    fun retriveUserData(callback: UserListingVMRepository.ApiCallBack<UsersDataResponse>)
}