package com.loogika.mikroisp.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.loogika.mikroisp.core.navigation.Screens
import com.loogika.mikroisp.core.navigation.util.NavigatorParams
import com.loogika.mikroisp.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : BaseViewModel() {

    fun openLogin() {
        viewModelScope.launch {
               appNavigator.setNavigatorEffect { NavigatorParams(screen = Screens.LoginScreen) }
        }
    }
}