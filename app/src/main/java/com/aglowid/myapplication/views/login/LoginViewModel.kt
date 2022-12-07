package com.aglowid.myapplication.views.login

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ObservableParcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aglowid.myapplication.R
import com.aglowid.myapplication.database.UsersDatabase
import com.aglowid.myapplication.model.XAcc
import com.aglowid.myapplication.network.model.LoginResponse
import com.aglowid.myapplication.network.model.RequestLoginUser
import com.aglowid.myapplication.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val usersDatabase: UsersDatabase
) : ViewModel() {

    val userData = loginRepository.user
    val xAcc = loginRepository.xAcc

    var observerUsername: ObservableField<String> = ObservableField<String>("")
    var observerErrorUsername = ObservableInt()
    var observerErrorEnabledUsername = ObservableBoolean(false)

    var observerPassword: ObservableField<String> = ObservableField<String>("")
    var observerErrorPassword = ObservableInt()
    var observerErrorEnabledPassword = ObservableBoolean(false)

    fun resetPasswordError() {
        observerErrorPassword.set(R.string.empty_string)
        observerErrorEnabledPassword.set(false)
    }

    fun resetUsernameError() {
        observerErrorUsername.set(R.string.empty_string)
        observerErrorEnabledUsername.set(false)
    }

    fun loginUser() = viewModelScope.launch(Dispatchers.IO) {
        if (observerUsername.get().isNullOrEmpty()) {
            observerErrorUsername.set(R.string.error_username)
            observerErrorEnabledUsername.set(true)
            return@launch
        }
        if (observerPassword.get().isNullOrEmpty()) {
            observerErrorPassword.set(R.string.error_password)
            observerErrorEnabledPassword.set(true)
            return@launch
        }
        val requestLoginUser = RequestLoginUser(
            observerUsername.get()!!,
            observerPassword.get()!!
        )
        loginRepository.loginUser(requestLoginUser)

    }
}