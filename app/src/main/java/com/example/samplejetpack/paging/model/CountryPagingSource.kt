package com.example.samplejetpack.paging.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.samplejetpack.paging.api.CountriesApi
import com.example.samplejetpack.paging.api.CountriesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class CountryPagingSource(private val countriesApi: CountriesApi): PagingSource<Int, Country>() {
    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        val position = params.key ?: 1
        return try {
         //   var countries:List<Country> = listOf()

            val countries = CountriesService.create().getCountries()
//            services.enqueue(object : Callback<List<Country>> {
//                override fun onResponse(
//                    call: Call<List<Country>>?,
//                    response: Response<List<Country>>?
//                ) {
//                    if (response!!.isSuccessful && response != null) {
//                        countries = response.body()
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
//
//                    println("failed")
//                }
//
//            })
            LoadResult.Page(
                data = countries,
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (countries.isEmpty()) null else position + (params.loadSize / 20)
            )
        }
     catch (exception: IOException) {
            LoadResult.Error(exception)
        } /*catch (exception: HttpException) {
            LoadResult.Error(exception)
        }*/
    }
}