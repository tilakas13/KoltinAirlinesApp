package com.apps.tilak.airlines.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apps.tilak.airlines.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor() : BaseViewModel() {

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    init {
        GlobalScope.launch {
            delay(3000)
            mutableLiveData.postValue(SplashState.ListAirlines)
        }
    }

    sealed class SplashState {
        object ListAirlines : SplashState()
    }

}