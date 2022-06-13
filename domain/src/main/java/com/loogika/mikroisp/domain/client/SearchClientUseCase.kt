package com.loogika.mikroisp.domain.client

import com.loogika.mikroisp.clientdata.model.Client
import com.loogika.mikroisp.clientdata.provider.ClientProvider
import javax.inject.Inject

class SearchClientUseCase @Inject constructor(private val clientProvider: ClientProvider) {

    fun search(query: String):List<Client>{
        return clientProvider.clients.filter { entity -> entity.dni?.contains(query) ?: false }
    }
}