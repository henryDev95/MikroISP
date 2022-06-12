package com.loogika.mikroisp.clientdata.repository

import com.loogika.mikroisp.network.di.model.client.ClientModel
import com.loogika.mikroisp.network.di.util.ErrorType
import com.loogika.mikroisp.network.di.util.ResultType
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    suspend fun getClients(parameter: Int):
            Flow<ResultType<List<ClientModel>, ErrorType>>
}