package com.aglowid.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("isDeleted")
    val isDeleted: Boolean
)
