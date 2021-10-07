package com.example.samplejetpack.paging.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.samplejetpack.paging.api.TodosApi
import com.example.samplejetpack.paging.api.TodosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class TodoPagingSource(private val todosApi: TodosApi) : PagingSource<Int, Todo>() {
    override fun getRefreshKey(state: PagingState<Int, Todo>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Todo> {
        val position = params.key ?: 1

        try {
            var todos: MutableList<Todo> = mutableListOf()
            todos = todosApi.getTodos()
            return LoadResult.Page(
                data = todos,
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (todos.isEmpty()) null else position + (params.loadSize / 20)
            )
        } catch (e: Exception) {
            println("error")
            return LoadResult.Error(e)
        }

    }
}