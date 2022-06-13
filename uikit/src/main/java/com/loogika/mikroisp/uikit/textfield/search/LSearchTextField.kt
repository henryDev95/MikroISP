package com.loogika.mikroisp.uikit.textfield.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LSearchTextField(
    modifier: Modifier = Modifier,
    initialText: String = "",
    placeholder: String,
    isRequestFocusRequired: Boolean = false,
    onSearchListener: (Boolean, String) -> Unit,
    onClearSearchListener: (() -> Unit)? = null,
) {
    // request focus
    val focusRequester = remember { FocusRequester() }

    // mutable state user input
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initialText)
        )
    }
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .focusRequester(focusRequester),
        onValueChange = {
            textFieldValue = it
            if (textFieldValue.text.isNotEmpty()) {
                onSearchListener(isFocused, textFieldValue.text)
            }
        },
        value = textFieldValue,
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                null,
                tint = if (isFocused) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
            )
        },
        trailingIcon = {
            if (textFieldValue.text.length > 1) {
                Icon(
                    Icons.Filled.Clear,
                    null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            onClick = {
                                textFieldValue = TextFieldValue(text = "")
                                onClearSearchListener?.invoke()
                            }
                        ),
                    tint = if (isFocused) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
                )
            }
        },
        singleLine = true,
        label = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Start
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = MaterialTheme.colors.primaryVariant,
            disabledLeadingIconColor = MaterialTheme.colors.secondaryVariant,
            disabledTextColor = MaterialTheme.colors.secondaryVariant,
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
            textColor = MaterialTheme.colors.primary
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchListener(isFocused, textFieldValue.text)
            }
        ),
    )

    DisposableEffect(Unit) {
        if (isRequestFocusRequired) {
            focusRequester.requestFocus()
        }
        onDispose { }
    }
}