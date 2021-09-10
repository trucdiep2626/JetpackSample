package com.example.samplejetpack.paging.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplejetpack.R
import com.example.samplejetpack.databinding.ActivityCountryBinding
import com.example.samplejetpack.paging.model.Country
import com.example.samplejetpack.paging.viewModel.CountryViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryBinding
    private lateinit var countryViewModel: CountryViewModel
      val adapter: CountryAdapter by lazy {
        CountryAdapter()
    }
    lateinit var countries: MutableList<Country>
    var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_country
        )
        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]


        val linearLayoutManager =
            LinearLayoutManager(this)
        binding.rvCountriesList.layoutManager = linearLayoutManager
        countries = mutableListOf()
        refreshData()
        binding.rvCountriesList.adapter = adapter

        refreshData()

    }

      fun refreshData() {
        job?.cancel()
          println("vvvvvvvvvvvvvvvvvvvvvvv")
        job = lifecycleScope.launch {
            countryViewModel.getCountries().collectLatest {
                adapter.submitData(it)
            }

        }
    }
}