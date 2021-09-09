package com.example.samplejetpack.workManager

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.samplejetpack.workManager.work.ScoreWorker

class WorkManagerViewModel () : ViewModel() {
    var application:Application = Application()
    var diemTp1: MutableLiveData<String> = MutableLiveData()
    var diemTp2: MutableLiveData<String> =MutableLiveData()
      var  outputWorkInfos: LiveData<MutableList<WorkInfo>>
      var workManager :WorkManager
      var result: MutableLiveData<String> =MutableLiveData()
    init {
        workManager  = WorkManager.getInstance(application)
        outputWorkInfos = workManager.getWorkInfosByTagLiveData("final")
        result.postValue("")
    }

    fun getFinalScore(){
        val dataBuilder = Data.Builder().putDouble("tp1", diemTp1.value!!.toDouble())
            .putDouble("tp2", diemTp2.value!!.toDouble()).build()

        val scoreRequest = OneTimeWorkRequestBuilder<ScoreWorker>()
            .addTag("final")
            .setInputData(dataBuilder).build()
//       var continuation = workManager.beginUniqueWork(
//            "getFinalScore",
//            ExistingWorkPolicy.REPLACE,
//           scoreRequest
//        )
//
//
//        continuation.enqueue()
        workManager.enqueue(scoreRequest)
        workManager.beginWith(scoreRequest).then(scoreRequest).enqueue()
        println("getFinalScore")
       // outputWorkInfos = workManager.getWorkInfosByTagLiveData("output")
      //  println
    }

      fun cancelWork() {
          workManager.cancelAllWork()
        workManager.cancelUniqueWork("getFinalScore")
    }



}