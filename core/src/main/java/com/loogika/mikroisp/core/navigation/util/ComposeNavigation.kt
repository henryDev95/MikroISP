package com.loogika.mikroisp.core.navigation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.loogika.mikroisp.core.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

object ComposeAppNavigator {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val effect: Channel<NavigatorParams> = Channel()
    val effectNavigator = effect.receiveAsFlow()

    fun setNavigatorEffect(builder: () -> NavigatorParams) {
        val effectValue = builder()
        scope.launch {
            effect.trySend(effectValue)
        }
    }
}

data class NavigatorParams(
    val screen: Screens,
    val params: Any? = null
)

@Composable
fun rememberAppNavigator(): ComposeAppNavigator {
    return remember { ComposeAppNavigator }
}