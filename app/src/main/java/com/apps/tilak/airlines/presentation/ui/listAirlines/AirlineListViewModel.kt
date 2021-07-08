package com.apps.tilak.airlines.presentation.ui.listAirlines

import androidx.lifecycle.liveData
import com.apps.tilak.airlines.base.BaseViewModel
import com.apps.tilak.airlines.data.repository.AirlinesRepository
import com.apps.tilak.airlines.presentation.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AirlineListViewModel
@Inject
constructor() : BaseViewModel() {

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