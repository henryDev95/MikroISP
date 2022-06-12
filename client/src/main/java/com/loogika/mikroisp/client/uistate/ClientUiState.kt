package com.loogika.mikroisp.client.uistate

import com.loogika.mikroisp.clientdata.model.Client


data class ClientUiState(
    val isLoading: Boolean = true,
    val isVisible: Boolean = true,
    val isError: Boolean = false,
    val data: List<Client> = emptyList()
) {
    companion object {
        val Empty = ClientUiState()
        val Loading = ClientUiState(isLoading = true)
    }
}
