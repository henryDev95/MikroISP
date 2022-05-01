package com.loogika.mikroisp.core.viewmodel

import androidx.lifecycle.ViewModel
import com.loogika.mikroisp.core.navigation.util.ComposeAppNavigator

abstract class BaseViewModel : ViewModel() {
    val appNavigator = ComposeAppNavigator
}