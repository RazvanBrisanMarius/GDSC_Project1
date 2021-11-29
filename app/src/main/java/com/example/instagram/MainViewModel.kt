package com.example.instagram

import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.business.model.UserPost
import com.example.business.processing.UserPostMapper

class MainViewModel : ViewModel() {

    val userPostLiveData = MutableLiveData<UserPost>()
    val name = MutableLiveData("apasa")
    var k : Int = 0
    var pausetime: Long = 0
    lateinit var chronometer: Chronometer
    lateinit var button: Button


    fun getUserPost() {
        val userPost = UserPostMapper.getUserPostMapper()
        userPostLiveData.value = userPost
    }

    fun modifyLiveData() {
        userPostLiveData.value = UserPost("Ati apasat pe buton.")
        //name.value = "Andi"
    }

    fun startTimer(chronometer: Chronometer)
    {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    fun stopTimer(chronometer: Chronometer)
    {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.stop()
    }

    fun pauseTimer(chronometer: Chronometer)
    {
        pausetime = SystemClock.elapsedRealtime()
        chronometer.stop()
    }

    fun unpauseTimer(chronometer: Chronometer)
    {
        val intervalOnPause: Long = SystemClock.elapsedRealtime() - pausetime
        chronometer.base = chronometer.base + intervalOnPause
        chronometer.start()
    }

    fun checkButtonState(button: Button, chronometer: Chronometer)
    {
        k++
        if(k % 2 != 0)
        {
            button.setText("Unpause Timer")
            pauseTimer(chronometer)
        }
        else
        {
            button.setText("Pause Timer")
            unpauseTimer(chronometer)
        }
    }

}