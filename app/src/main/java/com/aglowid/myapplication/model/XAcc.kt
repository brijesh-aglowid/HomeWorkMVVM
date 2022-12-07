package com.aglowid.myapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class XAcc(
    @PrimaryKey
    @SerializedName("X-Acc")
    val xAcc: String
) : Parcelable
