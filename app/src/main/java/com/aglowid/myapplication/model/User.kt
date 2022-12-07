package com.aglowid.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("isDeleted")
    val isDeleted: Boolean
)
