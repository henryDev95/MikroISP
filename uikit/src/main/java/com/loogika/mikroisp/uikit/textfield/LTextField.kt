package com.loogika.mikroisp.uikit.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loogika.mikroisp.uikit.style.Typography
import com.loogika.mikroisp.uikit.text.LMessageText
import com.loogika.mikroisp.uikit.text.enum.Type
import com.loogika.mikroisp.uikit.textfield.util.TextFieldState

@ExperimentalAnimationApi
@Composable
fun LTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    textFieldState: TextFieldState = remember { TextFieldState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    paddingValues: PaddingValues,
    isValidation: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    isError: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    isRequiredFocus: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    textStyle: TextStyle = MaterialTheme.typography.subtitle2,
    placeHolderTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    specificationMessage: String = "",
    maxLines: Int = 1,
    enabled: Boolean = true,
    widgetLeading: @Composable (() -> Unit)? = null,
    focusRequester: FocusRequester = FocusRequester(),
    validateText: (String) -> String = { it },
) {

    if (isError.value) {
        textFieldState.enableShowErrors()
        textFieldState.showErrors()
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            maxLines = maxLines,
            value = textFieldState.text,
            onValueChange = {
                textFieldState.text = validateText(it)
                if (isValidation.value) {
                    textFieldState.enableShowErrors()
                }
            },
            label = {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = label,
                        color = MaterialTheme.colors.primary,
                        style = placeHolderTextStyle,
                        textAlign = TextAlign.Center
                    )
                }
            },
            leadingIcon = widgetLeading,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    textFieldState.onFocusChange(focusState.isFocused)
                }
                .focusRequester(focusRequester)
                .padding(paddingValues),
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontFamily = textStyle.fontFamily,
                fontWeight = textStyle.fontWeight,
                fontSize = textStyle.fontSize
            ),
            isError = textFieldState.showErrors(),
            keyboardOptions = keyboardOptions.copy(
                imeAction = imeAction,
                capitalization = KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeAction()
                }
            ),
            shape = shape,
            enabled = enabled,
        )

        if (isValidation.value) {
            textFieldState.enableShowErrors()
            isError.value = true
            textFieldState.getError()?.let { error ->
                LMessageText(
                    message = error, Type.ERROR
                )
            }
        }

        AnimatedVisibility(
            visible = !textFieldState.showErrors(),
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                targetAlpha = 0.4f,
                animationSpec = tween(durationMillis = 1)
            )
        ) {
            Text(
                modifier = modifier.padding(top = 5.dp, start = 24.7.dp, end = 16.dp),
                text = specificationMessage,
                textAlign = TextAlign.Left,
                style = Typography.body2,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }

    LaunchedEffect(Unit) {
        if (isRequiredFocus) {
            focusRequester.requestFocus()
        }
    }
}