package com.loogika.mikroisp.clientdata.model

data class Client(
    val id: Long,
    val dni: String?,
    val userFirstName: String?,
    val userLastName: String?,
    val address: String?,
    val country: String?,
    val phone1: String?
)