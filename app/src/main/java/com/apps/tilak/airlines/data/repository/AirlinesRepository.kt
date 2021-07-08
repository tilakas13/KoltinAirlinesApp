package com.apps.tilak.airlines.data.repository

import com.apps.tilak.airlines.data.network.ApiHelper

class AirlinesRepository(private val apiHelper: ApiHelper) {
    suspend fun getListAirlines() = apiHelper.getListAirlines()
}