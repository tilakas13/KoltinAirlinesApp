package com.apps.tilak.airlines.view.listAirlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.apps.tilak.airlines.repository.AirlinesRepository
import com.apps.tilak.airlines.utils.Resource
import kotlinx.coroutines.Dispatchers


class AirlineListViewModel() : ViewModel() {

   private lateinit var airlineRepository: AirlinesRepository

    fun setRepository(airlinesRepository: AirlinesRepository) {
        this.airlineRepository = airlinesRepository
    }

    fun getListAirlines() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = airlineRepository.getListAirlines()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }




}