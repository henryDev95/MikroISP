package com.loogika.mikroisp.network.di.webservice

import com.loogika.mikroisp.network.di.model.client.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceApi {

    @GET("client/findAllClients")
    suspend fun getClients(@Query("institution_id") institutionId: Int) : Response<Data> // para obtener la lista de respuesta

}