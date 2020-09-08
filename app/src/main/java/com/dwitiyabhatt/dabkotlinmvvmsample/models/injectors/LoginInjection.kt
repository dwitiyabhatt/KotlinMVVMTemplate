package com.cmexpertise.yogakotlin.models.injectors

import androidx.lifecycle.ViewModelProvider
import com.cmexpertise.yogakotlin.models.api_repositories.LoginVMRepository
import com.cmexpertise.yogakotlin.models.factory_providers.LoginFactoryProvider
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor


object LoginInjection {

    private val LOGIN_VM_IMPLEMENTOR: LoginVMImplementor = LoginVMRepository()

    private val loginViewModelFactory = LoginFactoryProvider(LOGIN_VM_IMPLEMENTOR)

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return loginViewModelFactory
    }

}