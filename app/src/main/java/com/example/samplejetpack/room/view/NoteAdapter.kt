package com.example.samplejetpack.room.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samplejetpack.R
import com.example.samplejetpack.databinding.NoteItemBinding
import com.example.samplejetpack.room.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    var notes: MutableList<Note>
    val onClick: (Note) -> Unit
    val onDelete: (Note) -> Unit


    constructor(
        notes: MutableList<Note>, onClick: (Note) -> Unit,
        onDelete: (Note) -> Unit
    ) : super() {
        this.notes = notes
        this.onClick = onClick
        this.onDelete = onDelete
    }

    fun updateNotes(newNotes: MutableList<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tvTitle)
        var description: TextView = itemView.findViewById(R.id.tvDescription)
        var icDelete: ImageView = itemView.findViewById(R.id.icDelete)
        var item: View = itemView.findViewById(R.id.noteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = notes[position]
        println(note.title)
        holder.title.text = note.title
        println(holder.title.text)
        holder.description.setText(note.description)
        holder.icDelete.setOnClickListener { onDelete(note) }
        holder.item.setOnClickListener { onClick(note) }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

}