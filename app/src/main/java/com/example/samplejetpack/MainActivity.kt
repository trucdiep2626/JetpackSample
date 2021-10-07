package com.example.samplejetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.samplejetpack.databinding.ActivityMainBinding
import com.example.samplejetpack.navigation.NavigationMainActivity
import com.example.samplejetpack.paging.view.TodoActivity
import com.example.samplejetpack.room.view.NoteActivity
import com.example.samplejetpack.workManager.WorkManagerActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.tvWorkManager.setOnClickListener{
            val intent = Intent(this@MainActivity, WorkManagerActivity::class.java)
            startActivity(intent)
        }

        binding.tvRoom.setOnClickListener{
            val intent = Intent(this@MainActivity, NoteActivity::class.java)
            startActivity(intent)
        }

        binding.tvPaging.setOnClickListener{
            val intent = Intent(this@MainActivity, TodoActivity::class.java)
            startActivity(intent)
        }

        binding.tvNavigation.setOnClickListener{
            val intent = Intent(this@MainActivity, NavigationMainActivity::class.java)
            startActivity(intent)
        }

    }
}