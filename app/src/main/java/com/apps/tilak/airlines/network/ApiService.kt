package com.apps.tilak.airlines.network

import com.apps.tilak.airlines.model.AirlineItem
import retrofit2.http.GET

interface ApiService {

    @GET("h/mobileapis/directory/airlines")
    suspend fun getListAirlines(): List<AirlineItem>

}