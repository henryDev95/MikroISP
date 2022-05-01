package com.hapicorp.imhapi.login.components.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loogika.mikroisp.login.states.EmailState
import com.loogika.mikroisp.login.states.PasswordState
import com.loogika.mikroisp.core.R
import com.loogika.mikroisp.login.BuildConfig
import com.loogika.mikroisp.login.viewmodel.LoginViewModel
import com.loogika.mikroisp.uikit.button.LButton
import com.loogika.mikroisp.uikit.text.LTextRegular
import com.loogika.mikroisp.uikit.textfield.LPasswordTextField
import com.loogika.mikroisp.uikit.textfield.LTextField


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun LoginContent(
    modifier: Modifier,
    viewModel: LoginViewModel,
) {
    //context
    val context = LocalContext.current

    val passwordState by remember {
        mutableStateOf(
            PasswordState(
                context = context
            )
        )
    }

    val emailState = remember {
        EmailState(
            context = context
        )
    }

    // error connection state
    var errorConnection by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        val focusRequester = remember { FocusRequester() }

        val isValidation = remember {
            mutableStateOf(false)
        }
        val isError: MutableState<Boolean> = remember {
            mutableStateOf(false)
        }

        LTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.login_username),
            textFieldState = emailState,
            paddingValues = PaddingValues(start = 20.dp, end = 20.dp, top = 30.dp),
            isValidation = isValidation,
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LPasswordTextField(
            label = stringResource(id = R.string.login_password),
            passwordState = passwordState,
            modifier = Modifier
                .focusRequester(focusRequester)
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            onImeAction = {
                isValidation.value = true
                isError.value = true
                if (passwordState.isValid) {
                    //viewModel.validateData(emailState.text, passwordState.text)
                }
            },
            isValidation = isValidation,
            isError = isError
        )
        Spacer(modifier = Modifier.height(5.dp))

        LButton(
            onClick = {
                isValidation.value = true
                isError.value = true
                if (passwordState.isValid) {
                    //viewModel.validateData(emailState.text, passwordState.text)
                }
            },
            text = stringResource(id = R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 30.dp)
        )


        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val version = pInfo.versionName
        LTextRegular(
            modifier = Modifier.fillMaxWidth(),
            text = "${ if (BuildConfig.DEBUG) stringResource(id = R.string.debug_version) else stringResource(id = R.string.version)
            } $version",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}
