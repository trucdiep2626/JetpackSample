package com.example.samplejetpack.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.samplejetpack.paging.api.TodosService
import com.example.samplejetpack.paging.model.Todo
import com.example.samplejetpack.paging.model.TodoPagingSource
import kotlinx.coroutines.flow.Flow


class TodoRepository :ITodoRepository{

     override fun getTodosFromApi(): Flow<PagingData<Todo>> = Pager(
        config = PagingConfig(pageSize = 20,enablePlaceholders = false),
        pagingSourceFactory = { TodoPagingSource(TodosService.create())}
    ).flow
}