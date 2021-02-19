package com.apps.tilak.airlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.tilak.airlines.network.ApiHelper
import com.apps.tilak.airlines.view.airlineList.AirlineListViewModel
import com.apps.tilak.airlines.repository.AirlinesRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineListViewModel::class.java)) {
            return AirlineListViewModel(AirlinesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}