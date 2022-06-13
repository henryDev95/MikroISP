package com.loogika.mikroisp.client.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loogika.mikroisp.client.uistate.ClientUiState
import com.loogika.mikroisp.client.uistate.SearchUiState
import com.loogika.mikroisp.client.viewmodel.ClientsViewModel
import com.loogika.mikroisp.client.viewmodel.SearchViewModel
import com.loogika.mikroisp.client.widget.ClientWidget
import com.loogika.mikroisp.core.R
import com.loogika.mikroisp.core.flow.rememberFlowWithLifecycle
import com.loogika.mikroisp.uikit.divider.LDivider
import com.loogika.mikroisp.uikit.text.LTextHeadingBoldTitle
import com.loogika.mikroisp.uikit.textfield.search.LSearchTextField
import com.loogika.mikroisp.uikit.topbar.LTopAppBar

@Composable
fun ClientScreen(
    viewModel: ClientsViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
){
    val clientsState by rememberFlowWithLifecycle(viewModel.clientsState)
        .collectAsState(initial = ClientUiState.Empty)

    val searchState by rememberFlowWithLifecycle(flow = searchViewModel.searchTickersState)
        .collectAsState(initial = SearchUiState.Empty)

    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            LTopAppBar(
                action = stringResource(id = R.string.client_new),
                actionListener = {
                    //NewClientListener()
                },
                showBackButton = false
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AnimatedVisibility(visible = clientsState.isVisible) {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    ) {
                        LTextHeadingBoldTitle(
                            Modifier
                                .placeholder(
                                    visible = clientsState.isLoading,
                                    highlight = PlaceholderHighlight.shimmer()
                                )
                                .padding(start = 10.dp),
                            text = stringResource(id = R.string.client_title),
                            style = MaterialTheme.typography.h4
                        )

                        LDivider(
                            paddingValues = PaddingValues(
                                top = 12.dp,
                                bottom = 10.dp
                            )
                        )

                        LSearchTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp, top = 10.dp)
                                .placeholder(
                                    visible = clientsState.isLoading,
                                    highlight = PlaceholderHighlight.shimmer()
                                ),
                            initialText = searchState.lastSearch,
                            placeholder = stringResource(id = R.string.client_title),
                            onSearchListener = { _, search ->
                                searchViewModel.search(query = search)
                            },
                            isRequestFocusRequired = false,
                            onClearSearchListener = {
                                searchViewModel.search("")
                            }
                        )
                    }
                }
            }

            if(searchState.clients.isEmpty()){
                items(items = clientsState.data) { item ->
                    ClientWidget(
                        item = item,
                        itemClickListener = {

                        },
                        paddingValues = PaddingValues(start = 20.dp, end = 20.dp)
                    )

                    LDivider(
                        paddingValues = PaddingValues(
                            start = 30.dp,
                            end = 30.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                    )
                }
            }

            items(items = searchState.clients) { item ->
                ClientWidget(
                    item = item,
                    itemClickListener = {

                    },
                    paddingValues = PaddingValues(start = 20.dp, end = 20.dp)
                )

                LDivider(
                    paddingValues = PaddingValues(
                        start = 30.dp,
                        end = 30.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getClients(1)
    }
}