package com.cmexpertise.yogakotlin.models.factory_providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmexpertise.yogakotlin.models.mvvm_implementors.LoginVMImplementor
import com.cmexpertise.yogakotlin.viewmodels.LoginViewModel


class LoginFactoryProvider(private val loginVMImplementor: LoginVMImplementor) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
              return LoginViewModel(loginVMImplementor) as T
    }

}