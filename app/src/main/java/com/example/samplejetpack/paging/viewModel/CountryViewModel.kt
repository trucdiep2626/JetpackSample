package com.example.samplejetpack.paging.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.samplejetpack.paging.model.Country
import com.example.samplejetpack.paging.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class CountryViewModel():ViewModel() {
      var countryRepository: CountryRepository=CountryRepository()
      fun getCountries():Flow<PagingData<Country>> = countryRepository.getCountriesFromApi().cachedIn(viewModelScope)
}