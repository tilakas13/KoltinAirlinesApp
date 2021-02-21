package com.apps.tilak.airlines.network

import com.apps.tilak.airlines.constants.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var client = OkHttpClient()
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}