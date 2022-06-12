package com.loogika.mikroisp.clientdata.datasource

import com.loogika.mikroisp.clientdata.repository.ClientRepository
import com.loogika.mikroisp.network.di.DefaultDispatcher
import com.loogika.mikroisp.network.di.model.client.ClientModel
import com.loogika.mikroisp.network.di.util.ErrorType
import com.loogika.mikroisp.network.di.util.ResultType
import com.loogika.mikroisp.network.di.webservice.WebServiceApi
import com.loogika.mikroisp.network.exception.CustomException
import com.loogika.mikroisp.network.exception.UnAuthorizedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class ClientDataSource @Inject constructor(
    private val api: WebServiceApi,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ClientRepository {
    override suspend fun getClients(parameter: Int): Flow<ResultType<List<ClientModel>, ErrorType>> {
        val newsWatchListResult = kotlin.runCatching {
            val queryResult = api.getClients(parameter)
            queryResult.body()?.entities ?: emptyList()
        }

        return flow {
            newsWatchListResult
                .onSuccess { entities ->
                    emit(
                        ResultType.Success(
                            entities
                        )
                    )
                }.onFailure { ex ->
                    if (ex is HttpException) {
                        emit(
                            ResultType.Error(
                                ErrorType.NetworkHttpExceptionWithCode(
                                    retrofitHttpException = ex
                                )
                            )
                        )
                    }

                    if (ex is CustomException) {
                        emit(
                            ResultType.Error(
                                ErrorType.Error(ex.error)
                            )
                        )
                    }

                    if (ex is UnAuthorizedException) {
                        emit(
                            ResultType.Error(
                                ErrorType.NetworkAuthHttpException
                            )
                        )
                    }
                    emit(ResultType.Error(ErrorType.Exception(ex)))
                }
        }.flowOn(defaultDispatcher)
    }
}