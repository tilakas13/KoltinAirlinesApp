package com.apps.tilak.airlines.data.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getListAirlines() = apiService.getListAirlines()
}