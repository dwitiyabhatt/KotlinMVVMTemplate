package com.dwitiyabhatt.dabkotlinmvvmsample.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cmexpertise.yogakotlin.models.injectors.LoginInjection
import com.cmexpertise.yogakotlin.models.injectors.UserInjection
import com.cmexpertise.yogakotlin.models.pojo.LoginResponse
import com.cmexpertise.yogakotlin.utils.AppConstants
import com.cmexpertise.yogakotlin.utils.CommonMethods
import com.cmexpertise.yogakotlin.viewmodels.LoginViewModel
import com.cmexpertise.yogakotlin.viewmodels.UserListingViewModel
import com.cmexpertise.yogakotlin.views.activities.BaseActivity
import com.dwitiyabhatt.dabkotlinmvvmsample.R
import com.dwitiyabhatt.dabkotlinmvvmsample.SampleAplication
import com.dwitiyabhatt.dabkotlinmvvmsample.databinding.ActivityLoginBinding
import com.dwitiyabhatt.dabkotlinmvvmsample.databinding.ActivityUserListBinding
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UserDetails
import com.dwitiyabhatt.dabkotlinmvvmsample.views.adapters.UserListAdapter
import kotlinx.android.synthetic.main.activity_user_list.*


class UserListingActivity : BaseActivity(), UserListAdapter.Interaction {

    private lateinit var binding : ActivityUserListBinding
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userListingViewMOdel: UserListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setBinding()
        initializeViewMOdel()
        initRecyclerView()
        userListingViewMOdel.retriveUserData()

    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
    }

    private fun initRecyclerView(){

        rvUserList.apply {
            layoutManager = LinearLayoutManager(this@UserListingActivity)
            userListAdapter = UserListAdapter(this@UserListingActivity)
            adapter = userListAdapter
        }
    }

    private fun addDataSet(data :List<UserDetails>){
        userListAdapter.submitList(data)
    }

    override fun onItemSelected(position: Int, item: UserDetails) {
        TODO("Not yet implemented")
        Toast.makeText(applicationContext,"Clicked "+item.firstName, Toast.LENGTH_SHORT).show()
    }

    private fun initializeViewMOdel() {
        userListingViewMOdel = ViewModelProvider(
            this,
            UserInjection.provideViewModelFactory()
        ).get(UserListingViewModel::class.java)

        userListingViewMOdel.loginResonseData.observe(this,userDataObserver)
        userListingViewMOdel.isViewLoading.observe(this,isViewLoadingObserver)
        userListingViewMOdel.onMessageError.observe(this, errorMessageObserver)
    }

    private val userDataObserver = Observer<List<UserDetails>>{ loginResponseData ->
        addDataSet(loginResponseData)

    }

    private val isViewLoadingObserver = Observer<Boolean>{isLoading ->
        if(isLoading) showHideProgress(true,binding.layoutProgress.frProgress)
        else showHideProgress(false,binding.layoutProgress.frProgress)

    }

    private val errorMessageObserver = Observer<String>{message ->
        showToast(message)
    }
}
