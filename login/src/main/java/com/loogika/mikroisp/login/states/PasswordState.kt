package com.loogika.mikroisp.login.states

import android.content.Context
import com.loogika.mikroisp.core.R
import com.loogika.mikroisp.uikit.textfield.util.TextFieldState

class PasswordState(context: Context) :
    TextFieldState(validator = {
        isPasswordValid(context = context, it.trim())
    }, errorFor = {
        passwordValidationError()
    })

private var errorMessage = mutableListOf<String>()


fun isPasswordValid(context: Context, password: String): Boolean {
    val valid = password.isNotEmpty()
    getErrorMessage(context = context)
    return valid
}

private fun getErrorMessage(context: Context) {
    errorMessage.clear()
    errorMessage.add(context.getString(R.string.field_required))
}

private fun passwordValidationError(): String {
    var message = ""
    errorMessage.forEach {
        message =  "$message $it"
    }
    return message
}