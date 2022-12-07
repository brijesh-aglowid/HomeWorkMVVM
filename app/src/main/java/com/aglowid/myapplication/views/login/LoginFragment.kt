package com.aglowid.myapplication.views.login

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        viewModel.xAcc.observe(viewLifecycleOwner) { token ->
            Log.d("X_ACC", token)
        }

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                requireContext().showAlert(
                    "Login Successful.",
                    "Logged in for ${user.userName}"
                )
            }
        }
    }

    private fun Context.showAlert(
        title: String,
        message: String? = null
    ) {
        val alertDialog: AlertDialog =
            AlertDialog
                .Builder(this)
                .setTitle(title)
                .setPositiveButton(
                    "Ok"
                ) { dialogInterface, _ -> dialogInterface.dismiss() }
                .create()
        if (message != null) alertDialog.setMessage(message)

        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}