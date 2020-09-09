package com.cmexpertise.yogakotlin.models.network_data

import com.cmexpertise.yogakotlin.models.pojo.LoginResponse
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UsersDataResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiClient {

    //base url
    private val BASE_URL = "https://reqres.in/api/"

    //apis
    const val METHOD_LOGIN = "login"
    const val METHOD_USERS = "users"

    //serving interface
    private var serviceApiInterface : ServiceApiInterface? = null

    fun build() : ServiceApiInterface?{

        // create  api builder
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        // set http logger
        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(httpLoggingInterceptor)


        // retrofit object
        var retrofit: Retrofit = builder.client(httpClient.build()).build()

        // Service API interface
        serviceApiInterface = retrofit.create(ServiceApiInterface::class.java)

        return serviceApiInterface as ServiceApiInterface

    }



    interface ServiceApiInterface{

        @FormUrlEncoded
        @POST(METHOD_LOGIN)
        fun callLoginApi(@Field("email") email : String,
                         @Field("password") password : String): Call<LoginResponse>


        @GET(METHOD_USERS)
        fun callUserListApi(): Call<UsersDataResponse>


    }
}