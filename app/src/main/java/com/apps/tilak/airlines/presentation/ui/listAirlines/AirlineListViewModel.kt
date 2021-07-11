package com.apps.tilak.airlines.presentation.ui.listAirlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.apps.tilak.airlines.base.BaseViewModel
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.data.repository.AirlinesRepository
import com.apps.tilak.airlines.presentation.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirlineListViewModel
@Inject
constructor(private val airlinesRepository: AirlinesRepository) : BaseViewModel() {

    private val _res = MutableLiveData<Resource<List<AirlineItem>>>()
    val res : LiveData<Resource<List<AirlineItem>>>
        get() = _res

   /* fun setRepository(airlinesRepository: AirlinesRepository) {
        this.airlineRepository = airlinesRepository
    }*/

//    fun getListAirlines() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = airlinesRepository.getListAirlines()))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    public fun getListAirlines()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        airlinesRepository.getListAirlines().let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }


}