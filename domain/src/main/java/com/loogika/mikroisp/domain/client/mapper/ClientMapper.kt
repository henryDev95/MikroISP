package com.loogika.mikroisp.domain.client.mapper

import com.loogika.mikroisp.clientdata.model.Client
import com.loogika.mikroisp.network.di.model.client.ClientModel

internal val ClientModel.toClient: Client
    get() = Client(
        id, dni, userFirstName, userLastName, address, country, phone1
    )
