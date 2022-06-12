package com.loogika.mikroisp.network.di.model.client

import com.google.gson.annotations.SerializedName
import com.loogika.mikroisp.network.di.model.client.ClientModel

data class Data(
    @SerializedName("totalRows") val totalRows : String,
    @SerializedName("entities") val entities: List<ClientModel>)