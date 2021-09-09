package com.example.samplejetpack.workManager.work

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class ScoreWorker(context: Context, params:WorkerParameters):Worker(context, params) {
    override fun doWork(): Result {
        val tp1 = inputData.getDouble("tp1", 0.0)
        val tp2 = inputData.getDouble("tp2", 0.0)
        var final = tp1*0.3 + tp2*0.7

        try {
           println("getting")

           if(final<4.0)
           { var outputData = workDataOf("final" to "F")
            println(outputData)
            return Result.success(outputData)}
           if(final in 4.0..5.4) {
               var outputData = workDataOf("final" to "D")
               println(outputData)
               return Result.success(outputData)
           }
           if(final in 5.5..6.9) {
               var outputData = workDataOf("final" to "C")
               println(outputData)
               return Result.success(outputData)
           }
           if(final in 7.0..8.4) {
               var  outputData = workDataOf("final" to "B")
               println(outputData)
               return Result.success(outputData)
           }
           if(final in 8.5..10.0) {
               var  outputData = workDataOf("final" to "A")
               println(outputData)
               return Result.success(outputData)
           }

       } catch (e: Exception) {
            return Result.failure()
       }

        return Result.failure()
    }
}