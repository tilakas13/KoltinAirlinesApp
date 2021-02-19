package com.apps.tilak.airlines.ui.airlineList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.apps.tilak.airlines.utils.Resource
import com.apps.tilak.airlines.repository.AirlinesRepository
import kotlinx.coroutines.Dispatchers


class AirlineListViewModel(private val mainRepository: AirlinesRepository) : ViewModel() {
    fun getListAirlines() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getListAirlines()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}