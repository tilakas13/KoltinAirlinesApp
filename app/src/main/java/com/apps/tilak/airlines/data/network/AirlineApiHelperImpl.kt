package com.apps.tilak.airlines.data.network

import com.apps.tilak.airlines.data.model.AirlineItem
import retrofit2.Response
import javax.inject.Inject

class AirlineApiHelperImpl
@Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getListAirlines():
            Response<List<AirlineItem>> = apiService.getListAirlines()

}