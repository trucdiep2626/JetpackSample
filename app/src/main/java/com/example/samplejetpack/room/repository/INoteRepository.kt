package com.example.samplejetpack.room.repository

import androidx.lifecycle.LiveData
import com.example.samplejetpack.room.model.Note

interface INoteRepository {

    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
    fun getAllNote(): LiveData<List<Note>>
}