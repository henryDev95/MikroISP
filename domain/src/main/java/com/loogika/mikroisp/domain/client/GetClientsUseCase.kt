package com.loogika.mikroisp.domain.client

import com.loogika.mikroisp.clientdata.datasource.ClientDataSource
import com.loogika.mikroisp.domain.client.mapper.toClient
import com.loogika.mikroisp.domain.core.usecase.FlowUseCase
import com.loogika.mikroisp.domain.core.viewstate.Uitate
import kotlinx.coroutines.flow.map
import com.loogika.mikroisp.clientdata.model.Client
import com.loogika.mikroisp.network.di.util.ErrorType
import com.loogika.mikroisp.network.di.util.ResultType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val newsRepository: ClientDataSource
) : FlowUseCase<Int, List<Client>>() {
    override suspend fun execute(parameters: Int)
            : Flow<Uitate<List<Client>>> =
        newsRepository
            .getClients(parameters)
                .map { result ->
                if (result is ResultType.Success) {
                    return@map Uitate.Success(
                        result.value.map {
                            it.toClient
                        }
                    )
                }

                if (result is ResultType.Error) {
                    val error = result.value
                    if (error is ErrorType.Error) {
                        return@map Uitate.FailureErrorModel(error.errorModel)
                    }
                }

                return@map Uitate.UnExpectedError
            }
}