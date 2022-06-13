package com.loogika.mikroisp.client.uistate

import com.loogika.mikroisp.clientdata.model.Client

data class SearchUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val emptyStateVisible: Boolean = false,
    val clients: List<Client> = emptyList(),
    val lastSearch: String = "",
) {
    companion object {
        val Empty = SearchUiState()
    }
}