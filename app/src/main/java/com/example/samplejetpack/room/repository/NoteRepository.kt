package com.example.samplejetpack.room.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.samplejetpack.room.database.NoteDao
import com.example.samplejetpack.room.database.NoteDatabase
import com.example.samplejetpack.room.model.Note

class NoteRepository :INoteRepository {
    val noteDao:NoteDao
    val noteDatabase:NoteDatabase
    val app:Application

    constructor(app:Application){
        this.app=app
        noteDatabase= NoteDatabase.getInstance(app)
        noteDao=noteDatabase.getNoteDao()
    }

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    override suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    override fun getAllNote():LiveData<List<Note>> = noteDao.getAllNote()
}