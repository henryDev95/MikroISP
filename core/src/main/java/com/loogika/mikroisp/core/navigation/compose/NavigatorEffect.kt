package com.loogika.mikroisp.core.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.loogika.mikroisp.core.navigation.util.NavigatorParams
import com.loogika.mikroisp.core.navigation.util.rememberAppNavigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigatorEffect(
    onNavigateTo: (NavigatorParams) -> Unit
) {
    val navigator = rememberAppNavigator()
    LaunchedEffect(Unit) {
        navigator.effectNavigator
            .onEach { effect ->
                onNavigateTo(effect)
            }
            .collect()
    }
}