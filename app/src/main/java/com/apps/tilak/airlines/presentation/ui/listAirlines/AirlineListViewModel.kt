package com.apps.tilak.airlines.presentation.ui.listAirlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apps.tilak.airlines.base.BaseViewModel
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.data.repository.AirlinesRepository
import com.apps.tilak.airlines.presentation.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirlineListViewModel
@Inject
constructor(private val airlinesRepository: AirlinesRepository) : BaseViewModel() {

    private val listAirlines = MutableLiveData<Resource<List<AirlineItem>>>()
    val res : LiveData<Resource<List<AirlineItem>>>
        get() = listAirlines

    fun getListAirlines()  = viewModelScope.launch {
        listAirlines.postValue(Resource.loading(null))
        airlinesRepository.getListAirlines().let {
            if (it.isSuccessful){
                listAirlines.postValue(Resource.success(it.body()))
            }else{
                listAirlines.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }


}