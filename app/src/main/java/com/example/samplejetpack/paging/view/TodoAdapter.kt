package com.example.samplejetpack.paging.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samplejetpack.R
import com.example.samplejetpack.paging.model.Todo

    class TodoAdapter : PagingDataAdapter<Todo,TodoAdapter.TodoViewHolder>(
        COUNTRY_COMPARATOR) {


        class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var title: TextView = itemView.findViewById(R.id.tvTitle)
            var id: TextView = itemView.findViewById(R.id.tvId)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            return TodoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
            )
        }


        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
         //   println(todos.size)
            val todo = getItem(position)
            holder.title.text = todo?.title
            holder.id.text = todo?.id.toString()

        }



    companion object {
        private val COUNTRY_COMPARATOR = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean =
                oldItem == newItem
        }
    }

}