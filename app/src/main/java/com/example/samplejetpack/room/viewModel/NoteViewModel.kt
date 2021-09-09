package com.example.samplejetpack.room.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.samplejetpack.room.model.Note
import com.example.samplejetpack.room.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {
    val noteRepository: NoteRepository = NoteRepository(application)
    var title: MutableLiveData<String> = MutableLiveData()
    var description: MutableLiveData<String> = MutableLiveData()

    fun insertNote() = viewModelScope.launch {
        println(title.value!! + "  " + description.value!!)
        noteRepository.insertNote(Note(title = title.value!!, description = description.value!!))
        title.postValue("")
        description.postValue("")
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        note.title = title.value!!
        note.description = description.value!!
        noteRepository.updateNote(note)
        title.postValue("")
        description.postValue("")
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

    class NoteViewModelFactory(var application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                return NoteViewModel(application) as T
            }
            throw  IllegalAccessException("unable construct viewmodel")
        }
    }

}