package com.example.jetpacksample2.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityExampleFragment2Binding

class ExampleFragmentActivity : AppCompatActivity() {
  //  lateinit var binding : ActivityExampleFragment2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_fragment2)
//        binding = ActivityExampleFragment2Binding.inflate(layoutInflater)
   //     setContentView(binding.root)
        val dictionaryFragment= DictionaryFragment()
        val wordFragment = WordFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.dictionaryFragment,dictionaryFragment)
            commit()
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.wordFragment,wordFragment)
            commit()
        }

//
//        binding.btnDetail.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container,wordFragment)
//                commit()
//            }
//        }
//
//        binding.btnDictionary.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container,dictionaryFragment)
//                commit()
//            }
//        }


    }
}