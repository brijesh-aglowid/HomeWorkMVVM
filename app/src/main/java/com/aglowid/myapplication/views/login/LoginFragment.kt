package com.aglowid.myapplication.views.login

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aglowid.myapplication.BuildConfig
import com.aglowid.myapplication.R
import com.aglowid.myapplication.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    //To observe live data of the viewModel
    private fun observe() {
        viewModel.loginResponse.observe(viewLifecycleOwner) { it ->
            it?.let { loginResponse ->
                if (loginResponse.errorCode != "00") {
                    requireContext().showErrorAlert("Login Failed", loginResponse.errorMessage)
                } else if (loginResponse.user != null) {

                    requireContext()
                        .getSharedPreferences(
                            BuildConfig.APPLICATION_ID,
                            Context.MODE_PRIVATE
                        )
                        .edit()
                        .putString("Username", loginResponse.user.userName)
                        .apply()

                    requireContext().showErrorAlert(
                        "Successful Login",
                        "Welcome ${loginResponse.user.userName}"
                    )
                } else {
                    requireContext().showErrorAlert(
                        "Login",
                        "User not found"
                    )
                }
            }
        }
    }

    private fun Context.showErrorAlert(
        title: String,
        message: String
    ) {
        val alertDialog: AlertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                    "Ok"
                ) { dialogInterface, _ -> dialogInterface.dismiss() }
                .create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}