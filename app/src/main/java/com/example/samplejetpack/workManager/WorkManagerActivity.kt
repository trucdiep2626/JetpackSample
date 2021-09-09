package com.example.samplejetpack.workManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.samplejetpack.R
import com.example.samplejetpack.databinding.ActivityWorkManagerBinding

class WorkManagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityWorkManagerBinding
    lateinit var workManagerViewModel: WorkManagerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        workManagerViewModel= ViewModelProvider(this,)[WorkManagerViewModel ::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = workManagerViewModel
        workManagerViewModel.outputWorkInfos.observe(this, { listOfWorkInfo ->
          if (listOfWorkInfo.isNullOrEmpty()) {
                println("empty")
                return@observe
            }

            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()
                println("finished")
                val result = workInfo.outputData.getString("final")

                if (!result.isNullOrEmpty()) {
                    println("1111111"+result)
                    workManagerViewModel.result.postValue(result)
                }

            } else {
                showWorkInProgress()
            }
             })

    }

    private fun showWorkInProgress() {
        with(binding) {
            btnGetScore.visibility = View.GONE
            btnCancel.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showWorkFinished() {
        with(binding) {
            btnGetScore.visibility = View.VISIBLE
            btnCancel.visibility = View.GONE
            progressBar.visibility = View.GONE
            tvResult.visibility=View.VISIBLE
        }
    }
}