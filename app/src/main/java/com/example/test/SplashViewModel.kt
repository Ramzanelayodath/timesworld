package com.example.test


import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SplashViewModel : ViewModel() {

    private  val  value = MutableLiveData<Boolean>()
    val _value : MutableLiveData<Boolean> = value

     init {
         navigateToHome()
     }

    private fun navigateToHome()  =
        Handler(Looper.getMainLooper()).postDelayed({
            value.postValue(true)
        }, 3000) // 1000 milliseconds delay
}