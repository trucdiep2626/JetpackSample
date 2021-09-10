package com.example.samplejetpack.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.samplejetpack.paging.api.CountriesApi
import com.example.samplejetpack.paging.api.CountriesService
import com.example.samplejetpack.paging.model.Country
import com.example.samplejetpack.paging.model.CountryPagingSource
import kotlinx.coroutines.flow.Flow


class CountryRepository :ICountryRepository{

     override fun getCountriesFromApi(): Flow<PagingData<Country>> = Pager(
        config = PagingConfig(pageSize = 20,enablePlaceholders = false),
        pagingSourceFactory = { CountryPagingSource(CountriesService.create())}
    ).flow
}