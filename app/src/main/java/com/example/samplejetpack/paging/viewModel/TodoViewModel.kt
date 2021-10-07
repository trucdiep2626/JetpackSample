package com.example.samplejetpack.paging.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.samplejetpack.paging.model.Todo
import com.example.samplejetpack.paging.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoViewModel():ViewModel() {
      var todoRepository: TodoRepository=TodoRepository()
      fun getTodos():Flow<PagingData<Todo>> = todoRepository.getTodosFromApi().cachedIn(viewModelScope)
}