package com.apps.tilak.airlines.repository

import com.apps.tilak.airlines.network.ApiHelper

class AirlinesRepository(private val apiHelper: ApiHelper) {
    suspend fun getListAirlines() = apiHelper.getListAirlines()
}