package com.loogika.mikroisp.clientdata.provider

import com.loogika.mikroisp.clientdata.model.Client
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientProvider @Inject constructor() {
    var clients: List<Client> = emptyList()
}