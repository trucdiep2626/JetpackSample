package com.example.samplejetpack.room.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.samplejetpack.R
import com.example.samplejetpack.databinding.ActivityNoteBinding
import com.example.samplejetpack.databinding.ActivityWorkManagerBinding
import com.example.samplejetpack.room.model.Note
import com.example.samplejetpack.room.viewModel.NoteViewModel
import com.example.samplejetpack.workManager.WorkManagerViewModel

class NoteActivity : AppCompatActivity() {
    val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    lateinit var binding:ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.noteViewModel = noteViewModel
        val adapter:NoteAdapter= NoteAdapter(notes = mutableListOf(),onClick = onItemClick, onDelete = onItemDelete)
        adapter.notes.forEach { note ->println(note.title+note.description)  }
        binding.rvNotesList.adapter=adapter
        initControls()

    }

    fun initControls(){

        noteViewModel.getAllNote().observe(this,{
            val adapter:NoteAdapter= NoteAdapter(notes = it as MutableList<Note>,onClick = onItemClick, onDelete = onItemDelete)
           adapter.notes.forEach { note ->println(note.title+note.description)  }
            binding.rvNotesList.adapter=adapter
        })
    }
    val onItemClick:(Note) ->Unit={
        binding.btnUpdate.visibility= View.VISIBLE
        noteViewModel.title.postValue(it.title)
        noteViewModel.description.postValue(it.description)
        var note = it
        binding.btnUpdate.setOnClickListener{ noteViewModel.updateNote(note)
            binding.btnUpdate.visibility= View.GONE
        }
    }
    val onItemDelete:(Note) ->Unit={
        noteViewModel.deleteNote(note = it)
    }
}