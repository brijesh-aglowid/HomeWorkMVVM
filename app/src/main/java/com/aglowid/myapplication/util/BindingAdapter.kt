package com.aglowid.myapplication.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {

    @BindingAdapter("setTILError")
    @JvmStatic
    fun setError(textInputLayout: TextInputLayout, errorInt: Int) {
        if (errorInt != 0) {
            textInputLayout.error = textInputLayout.context.resources.getString(errorInt)
        }
    }

}