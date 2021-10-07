package com.example.samplejetpack.paging.api


import com.example.samplejetpack.paging.model.Todo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TodosApi {

    @GET("todos")
    suspend fun getTodos(
       //  @Query("page") page: Int,
    //    @Query("per_page") itemsPerPage: Int
   ):  MutableList<Todo>


}