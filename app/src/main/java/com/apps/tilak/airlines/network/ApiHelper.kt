package com.apps.tilak.airlines.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getListAirlines() = apiService.getListAirlines()
}