package com.example.samplejetpack.paging.repository

import androidx.paging.PagingData
import com.example.samplejetpack.paging.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    fun getCountriesFromApi(): Flow<PagingData<Country>>
}