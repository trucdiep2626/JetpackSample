package com.example.jetpacksample2.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel : ViewModel() {
    val wordRepository: WordRepository= WordRepository()
    var selectedWord: MutableLiveData<Word> = MutableLiveData()

    fun getDictionary(): MutableLiveData<List<Word>> = wordRepository.getDictionary()

    fun selectWord(word:Word){
        println(word.en)
        selectedWord.postValue(word)
    }

    fun getSelectedWordForDetail() : MutableLiveData<Word> = selectedWord
}