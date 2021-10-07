package com.example.jetpacksample2.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IWordRepository {
    fun getDictionary(): MutableLiveData<List<Word>>
}