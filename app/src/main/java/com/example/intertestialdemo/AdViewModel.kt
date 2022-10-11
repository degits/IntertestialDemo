package com.example.intertestialdemo

import android.os.CountDownTimer
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AdViewModel : ViewModel() {
    /*
    * No LiveData needed in this viewmodel
    * Because at UI, state was not observed(watch), instead when back button press , we <read> that value
    *
    * LiveData needed in this viewmodel
    * Because we have to load ad before show it.
    */

    //var startTime: Long = 0L

    //private var adShowStatus: Boolean = false

    /*fun getAdShowStatus(): Boolean {
        //logic
        var endTime = SystemClock.elapsedRealtime()
        val elapsedInMilliSeconds = endTime - startTime
        val elapsedInSeconds = elapsedInMilliSeconds / 1000F
        val elapsedInMinutes = elapsedInSeconds / 60F
        return elapsedInMinutes > 25

    }*/

    private val adLoadStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            startTimer()
        }
    }

    val timer = object : CountDownTimer(30000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            println("++++++++++++++++++++++++++++++++++++++++++++++++seconds remaining: " + millisUntilFinished / 1000)
            //Nothing
        }

        override fun onFinish() {
            //mTextField.setText("done!")
            //Updates the Load ad LiveData
            adLoadStatus.value = true

        }
    }

    fun startTimer() {
        println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++startTimer called")
        timer.start()
    }

    fun stopTimer() {
        println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++Stop timer called")
        timer.cancel()
    }

    fun getLoadStatus(): LiveData<Boolean> {
        return adLoadStatus
    }


}

/*
* LiveData used for =  Load Ad   (observable)
*
*
* -----Timer----
* 1. Using elapsed time                      (manual)
* 2. Using coroutine delay()   - recommend   (auto)
* 3. Using CountDownTimer      - recommend   (auto)
* --------------
*
*
*
* */