package com.example.composesample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue

class TodoViewModel :ViewModel(){
    var currentEditPosition by mutableStateOf(-1)
    var todos = mutableStateListOf<Todo>()

    val currentEditItem: Todo?
    get()= todos.getOrNull(currentEditPosition)

    fun addItem(item: Todo) {
        todos.add(item)
    }

    fun removeItem(item: Todo) {
        todos.remove(item)
        onEditDone() 
    }

    fun onEditItemSelected(item: Todo) {
        currentEditPosition = todos.indexOf(item)
    }
    fun onEditDone() {
        currentEditPosition = -1
    }
    fun onEditItemChange(item: Todo) {
        val currentItem = requireNotNull(currentEditItem)
        todos[currentEditPosition] = item
    }
}