package com.apps.tilak.airlines.data.repository

import com.apps.tilak.airlines.data.network.ApiHelper
import javax.inject.Inject

class AirlinesRepository
@Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getListAirlines() = apiHelper.getListAirlines()
}