package com.aglowid.myapplication.network.model

import com.aglowid.myapplication.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("data")
    val user: User?,
)
