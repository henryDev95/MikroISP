package com.loogika.mikroisp.login.states

import android.content.Context
import com.loogika.mikroisp.core.R
import com.loogika.mikroisp.uikit.textfield.util.TextFieldState
import java.util.regex.Pattern

class EmailState(context: Context) :
    TextFieldState(validator = {
        isEmailValid(context = context, it.trim())
    }, errorFor = {
        emailValidationError()
    })

private var errorMessage = mutableListOf<String>()
private var emailValid = true

private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

fun isEmailValid(context: Context, email: String): Boolean {
    emailValid = true

    var valid = true

    val pattern = Pattern.compile(EMAIL_VALIDATION_REGEX)
    val matcher = pattern.matcher(email)
    if (!matcher.matches()) {
        valid = false
        emailValid = false
    } else {
        emailValid = true
    }


    getErrorMessage(context)
    return valid
}

private fun getErrorMessage(context: Context) {
    errorMessage.clear()
    if (!emailValid) {
        errorMessage.add(context.getString(R.string.field_required))
    }
}

private fun emailValidationError(): String {
    var message = ""
    errorMessage.forEach {
        message = "$message $it"
    }

    return message
}
