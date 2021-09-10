package com.example.samplejetpack.paging.api

import com.example.samplejetpack.paging.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApi {

    @GET("all")
     fun getCountries(
//        @Query("page") page: Int,
//        @Query("per_page") itemsPerPage: Int
   ): List<Country>


}