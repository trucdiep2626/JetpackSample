package com.example.samplejetpack.paging.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {

    private val BASE_URL = "https://restcountries.eu/rest/v2/"

    fun create(): CountriesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
        return retrofit.create(CountriesApi::class.java)
    }
}