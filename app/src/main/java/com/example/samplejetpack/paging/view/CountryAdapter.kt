package com.example.samplejetpack.paging.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samplejetpack.R
import com.example.samplejetpack.paging.model.Country

    class CountryAdapter : PagingDataAdapter<Country,CountryAdapter.CountryViewHolder>(
        COUNTRY_COMPARATOR) {
        var countries: MutableList<Country> = mutableListOf()



        fun updateCountries(newCountries: MutableList<Country>) {
            countries.clear()
            countries.addAll(newCountries)
            notifyDataSetChanged()
        }

        class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var name: TextView = itemView.findViewById(R.id.tvCountryName)
            var capital: TextView = itemView.findViewById(R.id.tvCapital)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            return CountryViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
            )
        }


        override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
            println(countries.size)
            val country = getItem(position)
            holder.name.text = country?.name
            holder.capital.text = country?.capital

        }



    companion object {
        private val COUNTRY_COMPARATOR = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem
        }
    }

}