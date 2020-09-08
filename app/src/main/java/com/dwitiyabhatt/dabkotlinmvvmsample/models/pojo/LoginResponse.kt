package com.cmexpertise.yogakotlin.models.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("token")
    var token : String? = null
) {

}