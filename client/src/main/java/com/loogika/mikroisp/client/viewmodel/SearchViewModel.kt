package com.loogika.mikroisp.client.viewmodel

import androidx.lifecycle.viewModelScope
import com.loogika.mikroisp.client.uistate.SearchUiState
import com.loogika.mikroisp.core.viewmodel.BaseViewModel
import com.loogika.mikroisp.domain.client.SearchClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchClientUseCase
) : BaseViewModel() {
    private val _searchTickersInternalState = MutableStateFlow(SearchUiState.Empty)
    val searchTickersState get() = _searchTickersInternalState.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            val clients = searchUseCase.search(
                query
            )
            _searchTickersInternalState.value = SearchUiState(
                isLoading = false,
                isError = false,
                clients = clients,
                lastSearch = query,
                emptyStateVisible = clients.isEmpty() && query.isNotEmpty()
            )
        }
    }
}