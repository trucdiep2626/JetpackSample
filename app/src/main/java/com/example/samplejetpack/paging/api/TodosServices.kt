package com.example.samplejetpack.paging.api

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TodosService {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun create(): TodosApi {
        val retrofit = Retrofit.Builder()
       //     .addCallAdapterFactory(CallAdapter.Factory)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
        return retrofit.create(TodosApi::class.java)
    }
}