package com.loogika.mikroisp.client.viewmodel

import androidx.lifecycle.viewModelScope
import com.loogika.mikroisp.client.uistate.ClientUiState
import com.loogika.mikroisp.core.navigation.Screens
import com.loogika.mikroisp.core.navigation.util.NavigatorParams
import com.loogika.mikroisp.core.viewmodel.BaseViewModel
import com.loogika.mikroisp.domain.client.GetClientsUseCase
import com.loogika.mikroisp.domain.core.viewstate.collectAsFailure
import com.loogika.mikroisp.domain.core.viewstate.collectAsFailureErrorModel
import com.loogika.mikroisp.domain.core.viewstate.collectAsSuccess
import com.loogika.mikroisp.domain.core.viewstate.collectAsUnAuthorized
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase
) : BaseViewModel() {
    private val _clientsState = MutableStateFlow(ClientUiState.Empty)
    val clientsState = _clientsState.asStateFlow()

    fun getClients(parameter: Int) {
        viewModelScope.launch {
            _clientsState.value = ClientUiState.Loading
            getClientsUseCase(
                parameter
            ) collectAsSuccess { data ->
                _clientsState.value = ClientUiState(
                    data = data,
                    isLoading = false,
                    isVisible =data.isNotEmpty()
                )
            } collectAsFailureErrorModel {
                _clientsState.value = ClientUiState(
                    isError = true,
                    isLoading = false,
                    isVisible = false
                )
            } collectAsUnAuthorized {
                appNavigator.setNavigatorEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsFailure {
                _clientsState.value = ClientUiState(
                    isError = true,
                    isLoading = false,
                    isVisible = false
                )
            }
        }
    }
}