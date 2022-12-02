package com.aglowid.myapplication.repository

import com.aglowid.myapplication.network.LoginService
import com.aglowid.myapplication.network.model.LoginResponse
import com.aglowid.myapplication.network.model.RequestLoginUser
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {

    suspend fun loginUser(requestLoginUser: RequestLoginUser) : LoginResponse? {
        return try {
            loginService.loginUser(requestLoginUser)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}