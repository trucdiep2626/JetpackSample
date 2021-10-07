package com.example.samplejetpack.paging.repository

import androidx.paging.PagingData
import com.example.samplejetpack.paging.model.Todo
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    fun getTodosFromApi(): Flow<PagingData<Todo>>
}