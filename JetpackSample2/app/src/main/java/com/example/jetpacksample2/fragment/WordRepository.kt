package com.example.jetpacksample2.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WordRepository :IWordRepository{
    override fun getDictionary(): MutableLiveData<List<Word>> {
        var dictionary :MutableLiveData<List<Word>> = MutableLiveData()
        dictionary.postValue(
            listOf(
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Zoo", vn = "Sở thú"),
                Word(en = "Blue", vn = "Màu xanh"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
                Word(en = "Dictionary", vn = "Từ điển"),
            ))
        return dictionary
    }
}