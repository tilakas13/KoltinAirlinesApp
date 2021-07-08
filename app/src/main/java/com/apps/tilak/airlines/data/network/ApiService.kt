package com.apps.tilak.airlines.data.network

import com.apps.tilak.airlines.data.model.AirlineItem
import retrofit2.http.GET

interface ApiService {

    @GET("h/mobileapis/directory/airlines")
    suspend fun getListAirlines(): List<AirlineItem>

}