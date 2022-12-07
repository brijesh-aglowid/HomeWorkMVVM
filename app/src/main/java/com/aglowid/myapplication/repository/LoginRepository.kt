package com.aglowid.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aglowid.myapplication.database.UsersDatabase
import com.aglowid.myapplication.model.User
import com.aglowid.myapplication.model.XAcc
import com.aglowid.myapplication.network.LoginService
import com.aglowid.myapplication.network.model.RequestLoginUser
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService,
    private val usersDatabase: UsersDatabase
) {

    val xAcc: LiveData<String> = Transformations.map(usersDatabase.usersDao.getXAcc()) {
        it?.xAcc ?: ""
    }

    val user: LiveData<User?> = Transformations.map(usersDatabase.usersDao.getUser()) {
        it
    }

    suspend fun loginUser(requestLoginUser: RequestLoginUser) {
        val response = loginService.loginUser(requestLoginUser).execute()
        if (response.isSuccessful && response.body()?.errorCode == "00") {
            Log.d("ASDASD", "${response.body()?.errorCode}")
            response.body()?.user?.let { user ->
                usersDatabase.usersDao.insertUser(user)
                val token = response.headers()["X-Acc"]
                if (token != null) {
                    usersDatabase.usersDao.insert(XAcc(token))
                }

                Log.d("USERDATA", "$token ===>>> ${user.userName}")
            }

        }
    }
}