package com.apps.tilak.airlines.presentation.ui.utils
import com.apps.tilak.airlines.presentation.ui.utils.Status.ERROR
import com.apps.tilak.airlines.presentation.ui.utils.Status.LOADING
import com.apps.tilak.airlines.presentation.ui.utils.Status.SUCCESS

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String?
){
    companion object{

        fun <T> success(data:T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg:String, data:T?): Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data:T?): Resource<T>{
            return Resource(Status.LOADING, data, null)
        }

    }
}