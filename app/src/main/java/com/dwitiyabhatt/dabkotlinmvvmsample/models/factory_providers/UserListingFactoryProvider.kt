package com.cmexpertise.yogakotlin.models.factory_providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor
import com.cmexpertise.yogakotlin.models.mvvm_implementors.UserVMImplementor
import com.cmexpertise.yogakotlin.viewmodels.LoginViewModel
import com.cmexpertise.yogakotlin.viewmodels.UserListingViewModel


class UserListingFactoryProvider(private val userVMImplementor: UserVMImplementor) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
              return UserListingViewModel(userVMImplementor) as T
    }

}