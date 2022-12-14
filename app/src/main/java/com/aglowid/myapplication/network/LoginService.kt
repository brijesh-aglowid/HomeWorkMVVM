package com.aglowid.myapplication.network

import com.aglowid.myapplication.network.model.LoginResponse
import com.aglowid.myapplication.network.model.RequestLoginUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun loginUser(@Body request: RequestLoginUser): Call<LoginResponse>

}
