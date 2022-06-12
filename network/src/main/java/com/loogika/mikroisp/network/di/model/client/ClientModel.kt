package com.loogika.mikroisp.network.di.model.client

import com.google.gson.annotations.SerializedName

data class ClientModel(
    @SerializedName("id") val id: Long,
    @SerializedName("dni") val dni: String?,
    @SerializedName("userFirstName") val userFirstName: String?,
    @SerializedName("userLastName") val userLastName: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("phone1") val phone1: String?
)