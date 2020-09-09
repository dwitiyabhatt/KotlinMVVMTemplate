package com.cmexpertise.yogakotlin.models.injectors

import androidx.lifecycle.ViewModelProvider
import com.cmexpertise.yogakotlin.models.api_repositories.LoginVMRepository
import com.cmexpertise.yogakotlin.models.api_repositories.UserListingVMRepository
import com.cmexpertise.yogakotlin.models.factory_providers.LoginFactoryProvider
import com.cmexpertise.yogakotlin.models.factory_providers.UserListingFactoryProvider
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor
import com.cmexpertise.yogakotlin.models.mvvm_implementors.UserVMImplementor


object UserInjection {

    private val USER_VM_IMPLEMENTOR: UserVMImplementor = UserListingVMRepository()

    private val userListingViewModelFactory = UserListingFactoryProvider(USER_VM_IMPLEMENTOR)

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return userListingViewModelFactory
    }

}