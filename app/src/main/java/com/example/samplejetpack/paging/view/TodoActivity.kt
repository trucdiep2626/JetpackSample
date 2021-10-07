package com.example.samplejetpack.paging.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplejetpack.R
import com.example.samplejetpack.databinding.ActivityTodoBinding
import com.example.samplejetpack.paging.model.Todo
import com.example.samplejetpack.paging.viewModel.TodoViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    private lateinit var todoViewModel: TodoViewModel
    val adapter: TodoAdapter by lazy {
        TodoAdapter()
    }
    lateinit var countries: MutableList<Todo>
    var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_todo
        )
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]


        val linearLayoutManager =
            LinearLayoutManager(this)
        binding.rvTodosList.layoutManager = linearLayoutManager
        countries = mutableListOf()
        refreshData()
        binding.rvTodosList.adapter = adapter

  //      refreshData()

    }

    fun refreshData() {
        job?.cancel()
        println("vvvvvvvvvvvvvvvvvvvvvvv")
        job = lifecycleScope.launch {
            todoViewModel.getTodos().collectLatest {
                adapter.submitData(it)
            }

        }
    }
}