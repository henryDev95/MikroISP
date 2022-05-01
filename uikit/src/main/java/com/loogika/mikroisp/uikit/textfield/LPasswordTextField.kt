package com.loogika.mikroisp.uikit.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.loogika.mikroisp.uikit.R
import com.loogika.mikroisp.uikit.text.LMessageText
import com.loogika.mikroisp.uikit.text.enum.Type
import com.loogika.mikroisp.uikit.textfield.util.TextFieldState

@Composable
fun LPasswordTextField(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    isValidation: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    isError: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    textStyle: TextStyle = MaterialTheme.typography.subtitle2
) {
    val showPassword = remember { mutableStateOf(false) }

    if (isError.value) {
        passwordState.enableShowErrors()
        passwordState.showErrors()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = passwordState.text,
            onValueChange = {
                passwordState.text = it
                if (isValidation.value) {
                    passwordState.enableShowErrors()
                }
            },
            maxLines = 1,
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    passwordState.onFocusChange(focusState.isFocused)
                    if (isValidation.value) {
                        passwordState.enableShowErrors()
                    }
                },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontFamily = textStyle.fontFamily,
                fontWeight = textStyle.fontWeight,
                fontSize = textStyle.fontSize
            ),
            singleLine = true,
            label = {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        painter = if (showPassword.value)
                            painterResource(R.drawable.ic_eye)
                        else painterResource(
                            R.drawable.ic_eye_off
                        ),
                        contentDescription = "Password",
                        tint = MaterialTheme.colors.primary
                    )
                }
            },
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            isError = passwordState.showErrors(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeAction()
                }
            ),
            shape = shape
        )

        if (isValidation.value) {
            passwordState.enableShowErrors()
            passwordState.getError()?.let { error ->
                LMessageText(
                    message = error, type = Type.ERROR
                )
            }
        }
    }
}


