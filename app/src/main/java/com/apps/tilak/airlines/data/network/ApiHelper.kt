package com.apps.tilak.airlines.data.network

import com.apps.tilak.airlines.data.model.AirlineItem
import retrofit2.Response

interface ApiHelper {
    suspend fun getListAirlines(): Response<List<AirlineItem>>
}